//package com.arjunsaji.test.security;
//
//import com.arjunsaji.test.config.Constants;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@Order(102)
//public class Security102Product {
//    @Bean
//    SecurityFilterChain productSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        http.authorizeHttpRequests(request -> {
//            request.requestMatchers(Constants.API_V1_PRODUCT + "/**").permitAll();
//        });
//
//        return http.build();
//    }
//
//
//    //        http
////                .securityMatcher(Constants.API_V1_PRODUCT + "/**")
////                .authorizeHttpRequests(authorize-> {
////                    try {
////                        authorize
////                                .anyRequest()
////                                .hasAuthority(Constants.ROLE_ADMIN);
////                    } catch (Exception e) {
////                        throw new RuntimeException(e);
////                    }
////                });
//
//}
