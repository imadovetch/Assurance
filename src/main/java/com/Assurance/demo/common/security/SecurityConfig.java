package com.Assurance.demo.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    public BasicLoginHandler basicLoginHandler;

    // Define open routes (paths you want to exclude from CSRF protection)
    private List<RequestMatcher> openRoutes = Arrays.asList(
            new AntPathRequestMatcher("/register")
    );

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(openRoutes.toArray(new RequestMatcher[0])) // Convert List to array of RequestMatchers
                )
                .authorizeHttpRequests(
                        authorizeHttp -> {
                            authorizeHttp.requestMatchers("/", "/favicon.svg", "/css/*", "/error", "/register").permitAll(); // Public routes
                            authorizeHttp.anyRequest().authenticated(); // All other routes require authentication
                        }
                )
                .formLogin(withDefaults())  // Use Spring Security's default login form
                .authenticationProvider(basicLoginHandler) // Use your custom authentication provider
                .httpBasic(withDefaults()) // Basic authentication
                .build();
    }
}
