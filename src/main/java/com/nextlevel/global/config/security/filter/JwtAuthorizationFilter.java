package com.nextlevel.global.config.security.filter;

import com.nextlevel.global.config.security.jwt.TokenUtils;
import com.nextlevel.global.exception.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        List<String> notNecessaryTokenUrl = Arrays.asList("/login", "/images/**");

        if(notNecessaryTokenUrl.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        try {
            if (token != null && !token.equalsIgnoreCase("")) {
                if (TokenUtils.isValidToken(token)) {
                    String loginId = TokenUtils.getUserIdFromToken(token);
                    log.debug("[+] loginId Check: {}", loginId);

                    if (loginId != null && !loginId.equalsIgnoreCase("")) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    } else {
                        throw new ProfileApplicationException(ErrorCode.TOKEN_NOT_VALID);
                    }
                } else {
                    throw new ProfileApplicationException(ErrorCode.TOKEN_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            String logMessage = jsonResponseWrapper(e).getString("message");
            log.error(logMessage, e);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", true);
            jsonObject.put("message", "로그인 에러");

            writer.print(jsonObject);
            writer.flush();
            writer.close();
        }
        filterChain.doFilter(request, response);
    }

    private JSONObject jsonResponseWrapper(Exception e) {
        String resultMessage = "";

        if (e instanceof ExpiredJwtException) {
            resultMessage = "TOKEN expired";
        } else if (e instanceof SignatureException) {
            resultMessage = "TOKEN SignatureException Login";
        } else if (e instanceof JwtException) {
            resultMessage = "TOKEN Parsing JwtException";
        } else {
            resultMessage = "OTHER TOKEN ERROR";
        }

        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("status", 401);
        jsonMap.put("code", 9999);
        jsonMap.put("message", resultMessage);
        jsonMap.put("reason", e.getMessage());
        JSONObject jsonObject = new JSONObject(jsonMap);
        log.error(resultMessage, e);

        return jsonObject;
    }
}
