package com.nextlevel.global.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Slf4j
@Configuration
@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String failMessage = checkException(exception);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (PrintWriter printWriter = response.getWriter()) {
            log.debug("[+] CustomAuthFailureHandler: {}", failMessage);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("userInfo", null);
            resultMap.put("resultCode", 9999);
            resultMap.put("failMessage", failMessage);
            JSONObject jsonObject = new JSONObject(resultMap);

            printWriter.print(jsonObject);
            printWriter.flush();
        }
    }

    private static String checkException(AuthenticationException exception) {
        String failMessage = "";

        if (exception instanceof AuthenticationServiceException) {
            failMessage = "로그인 정보가 일치하지 않습니다.";
        } else if (exception instanceof LockedException) {
            failMessage = "계정이 잠겨있습니다.";
        } else if (exception instanceof DisabledException) {
            failMessage = "계정이 비활성화되었습니다.";
        } else if (exception instanceof AccountExpiredException) {
            failMessage = "계정이 만료되었습니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            failMessage = "인증 정보가 만료되었습니다.";
        } else {
            failMessage = exception.getMessage();
        }
        return failMessage;
    }
}
