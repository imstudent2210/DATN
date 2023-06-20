package com.graduate.touslestemp.exception;

import org.springframework.security.core.AuthenticationException;
/*
* @File:  UserAlreadyExistAuthenticationException.java com.graduate.touslestemp.exception
*
* @Author: TamNLT
* @Since: 20/6/2023 11:28 PM
* @Last update: 20/6/2023
*
* */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 5570981880007077317L;

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
