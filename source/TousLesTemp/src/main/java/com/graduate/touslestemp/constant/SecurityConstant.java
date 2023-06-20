package com.graduate.touslestemp.constant;

/*
* @File:  SecurityConstant.java com.graduate.touslestemp.constant
*
* @Author: TamNLT
* @Since: 20/6/2023 11:11 PM
* @Last update: 20/6/2023
*
* */
public class SecurityConstant {
    public static final String SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_REGISTER_URL = "/register/**";
    public static final String AUTH_LOGIN_URL = "/login";
    public static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**", "/swagger-ui/**",
            "/login", "/register/**", "/address/**",
            "/store/**", "/category/**", "/product/**",
            "/staff/**","/sendmail","/staffgroup/**","/size/**",
            "/", "/error", "/api/all", "/api/auth/**", "/oauth2/**",
            "/staff-timekeeping/**","/staff-salary/**"
    };
}
