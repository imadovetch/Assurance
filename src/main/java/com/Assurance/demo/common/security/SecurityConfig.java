//package com.Assurance.demo.common.security;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig {
//
//    private List<String> OpenRoutes = Arrays.asList("/register");
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers((RequestMatcher) OpenRoutes)
//                )
//                .authorizeHttpRequests(
//                        authorizeHttp -> {
//                            authorizeHttp.requestMatchers("/").permitAll();
//                            authorizeHttp.requestMatchers("/favicon.svg").permitAll();
//                            authorizeHttp.requestMatchers("/css/*").permitAll();
//                            authorizeHttp.requestMatchers("/error").permitAll();
//                            authorizeHttp.requestMatchers("/register").permitAll();
//                            authorizeHttp.anyRequest().authenticated();
//                        }
//                )
//                .formLogin(l -> l
//                        .successHandler((request, response, authentication) -> {
//                            System.out.println("Login successful for: " + authentication.getName());
//                        })
//                )
//
//                .logout(l -> l.logoutSuccessUrl("/"))
//                .oauth2Login(withDefaults())
//                .httpBasic(withDefaults())
//                .build();
//
//    }
//
//    @Bean
//    ApplicationListener<AuthenticationSuccessEvent> successListener() {
//        return event -> {
//            event.getAuthentication().setAuthenticated(true);
//            System.out.println("Loged succesfly");
//        };
//    }
//}
