package com.graduate.touslestemp.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @File: UserAlreadyExistAuthenticationException.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:24 AM
 * @Update: 21/6/2023
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 5570981880007077317L;

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
