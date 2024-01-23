package com.arjunsaji.test.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Order(100)
@EnableWebSecurity
public class SpringSecurityBasic {
}
