package com.arjunsaji.test.security;

import com.arjunsaji.test.config.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(101)
public class Security101User {

    @Bean
    SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request -> {

            request
                    .requestMatchers(Constants.API_V1_USER + "/login" ,Constants.API_V1_USER + "/signup").permitAll()
                    .requestMatchers(Constants.API_V1_USER + "/**").hasAuthority(Constants.ROLE_USER);

            request
                    .requestMatchers(Constants.API_V1_PRODUCT + "/list").hasAnyAuthority(Constants.ROLE_ADMIN,Constants.ROLE_USER)
                    .requestMatchers(Constants.API_V1_PRODUCT + "/**").hasAuthority(Constants.ROLE_ADMIN);

            request
                    .requestMatchers(Constants.API_V1_CART + "/**").hasAuthority(Constants.ROLE_USER);

        });

        return http.build();
    }
}
