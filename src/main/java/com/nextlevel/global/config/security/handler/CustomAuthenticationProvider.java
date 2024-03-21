package com.nextlevel.global.config.security.handler;

import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("2.CustomAuthenticationProvider");

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String loginId = token.getName();
        String userPassword = (String) token.getCredentials();

        SecurityUserDetailsDto securityUserDetailsDto = (SecurityUserDetailsDto) userDetailsService.loadUserByUsername(loginId);
        String encodePassword = bCryptPasswordEncoder().encode(securityUserDetailsDto.getPassword());

        if (!bCryptPasswordEncoder().matches(userPassword, encodePassword)) {
            throw new BadCredentialsException(securityUserDetailsDto.getUsername() + " Invalid password");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(securityUserDetailsDto, userPassword, securityUserDetailsDto.getAuthorities());
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
