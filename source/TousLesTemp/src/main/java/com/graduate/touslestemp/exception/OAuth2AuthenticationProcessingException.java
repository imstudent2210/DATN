package com.graduate.touslestemp.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @File: OAuth2AuthenticationProcessingException.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:24 AM
 * @Update: 21/6/2023
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    private static final long serialVersionUID = 3392450042101522832L;

    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}