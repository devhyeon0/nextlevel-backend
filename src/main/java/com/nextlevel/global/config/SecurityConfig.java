package com.nextlevel.global.config;

import com.nextlevel.domain.user.service.CustomUserDetailsService;
import com.nextlevel.global.config.security.filter.CustomAuthenticationFilter;
import com.nextlevel.global.config.security.filter.JwtAuthorizationFilter;
import com.nextlevel.global.config.security.handler.CustomAuthFailureHandler;
import com.nextlevel.global.config.security.handler.CustomAuthSuccessHandler;
import com.nextlevel.global.config.security.handler.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

import static org.springframework.http.HttpMethod.*;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthorizationFilter jwtAuthorizationFilter,
                                           CustomAuthenticationFilter customAuthenticationFilter) throws Exception {
        log.debug("[+] WebSecurityConfig Start");

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(POST, "/api/users").permitAll()
                        .requestMatchers(GET, "/api/posts/**").permitAll()
                        .requestMatchers(GET, "/api/comments/**").permitAll()
                        .requestMatchers(GET, "/api/categories/**").permitAll()
                        .requestMatchers(GET, "/api/post-reaction/**").permitAll()
                        .requestMatchers(GET, "/api/comment-reaction/**").permitAll()
                        .requestMatchers(POST, "/api/categories").hasRole("ADMIN")
                        .requestMatchers(PATCH, "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(DELETE, "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Content-Type", "Authorization", "X-XSRF-token"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(AuthenticationManager authenticationManager,
                                                                 CustomAuthSuccessHandler customAuthSuccessHandler,
                                                                 CustomAuthFailureHandler customAuthFailureHandler) {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthFailureHandler);
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthenticationProvider) {
        return new ProviderManager(Collections.singletonList(customAuthenticationProvider));
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(UserDetailsService userDetailsService) {
        return new CustomAuthenticationProvider(userDetailsService);
    }

    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(CustomUserDetailsService userDetailsService) {
        return new JwtAuthorizationFilter(userDetailsService);
    }

    private AuthorizationDecision isAdmin(Supplier<Authentication> authenticationSupplier,
                                          RequestAuthorizationContext requestAuthorizationContext) {
        return new AuthorizationDecision(
                authenticationSupplier.get()
                        .getAuthorities()
                        .contains(new SimpleGrantedAuthority("ADMIN")));
    }
}
