package com.nextlevel.global.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.dto.UserLoginDto;
import com.nextlevel.global.config.security.jwt.TokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.debug("3.CustomLoginSuccessHandler");

        UserLoginDto userDto = ((SecurityUserDetailsDto) authentication.getPrincipal()).getUserDto();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JSONObject userDtoObject = new JSONObject(objectMapper.writeValueAsString(userDto));

        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("userInfo", userDtoObject);

        if (Objects.equals(userDto.status(), "INACTIVE")) {
            responseMap.put("resultCode", 9001);
            responseMap.put("token", null);
            responseMap.put("failMessage", "휴면 계정입니다.");
        } else {
            responseMap.put("resultCode", 200);
            responseMap.put("failMessage", null);

            String token = TokenUtils.generateJwtToken(userDto);
            responseMap.put("token", token);

            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
        }

        JSONObject jsonObject = new JSONObject(responseMap);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println(jsonObject);
            printWriter.flush();
        }
    }
}
