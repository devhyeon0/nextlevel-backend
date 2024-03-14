package com.nextlevel.global.config.security.jwt;

import com.nextlevel.domain.user.dto.UserLoginDto;
import com.nextlevel.domain.user.dto.UserResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenUtils {

//    @Value("${jwt.secretKey}")
    //TODO: 임시 JWT Secret Key, 수정 필요
    private static final String jwtSecretKey = "dGhpc0lzU2VjcmV0S2V5SldUU2VjcmV0U2VjcmV0TmV4dExldmVsSnd0U2VjcmV0S2V5MjAyNDAzMTBXcml0ZQo=";
    private static final Key key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    private static final String JWT_TYPE = "JWT";
    private static final String ALGORITHM = "HS256";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";

    public static String generateJwtToken(UserLoginDto userDto) {
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(userDto))
                .setSubject(String.valueOf(userDto.email()))
                .setIssuer("profile")
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(createExpireDate());

        return builder.compact();
    }

    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);

            log.info("expireTime: {}", claims.getExpiration());
            log.info("email: {}", claims.get(EMAIL));
            log.info("username: {}", claims.get(USERNAME));

            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token Expired", e);
            return false;
        } catch (JwtException e) {
            log.error("Token Tampered", e);
            return false;
        } catch (NullPointerException e) {
            log.error("Token is null", e);
            return false;
        }
    }

    private static Date createExpireDate() {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(Duration.ofHours(8));
        return Date.from(expiryDate);
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", JWT_TYPE);
        header.put("alg", ALGORITHM);
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private static Map<String, Object> createClaims(UserLoginDto userDto) {
        Map<String, Object> claims = new HashMap<>();

        log.info("email: {}", userDto.email());
        log.info("username: {}", userDto.nickname());

        claims.put(EMAIL, userDto.email());
        claims.put(USERNAME, userDto.nickname());
        return claims;
    }

    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(token).getBody();
    }

    public static String getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get(EMAIL).toString();
    }
}
