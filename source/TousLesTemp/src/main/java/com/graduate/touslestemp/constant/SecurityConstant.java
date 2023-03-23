package com.graduate.touslestemp.constant;

public class SecurityConstant {
    public static final String SECRET = "tamnlt2";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_REGISTER_URL = "/register/**";
    public static final String AUTH_LOGIN_URL = "/login";
    public static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**", "/swagger-ui/**",
            "/login", "/register/**", "/address/**",
            "/store/**", "/category/**", "/product/**",
            "/staff/**"
    };
}
