package com.arjunsaji.test.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {

    public final static String API_V1 = "/api/v1";
    public final static String API_V1_USER = API_V1 + "/user";
    public final static String API_V1_CART = API_V1_USER + "/cart";
    public final static String API_V1_ORDER = API_V1_USER + "/order";
    public final static String API_V1_ADMIN = API_V1 + "/admin";
    public final static String API_V1_PRODUCT = API_V1_ADMIN + "/product";
    public final static String ROLE_ADMIN = "ADMIN";
    public final static String ROLE_USER = "USER";
}
