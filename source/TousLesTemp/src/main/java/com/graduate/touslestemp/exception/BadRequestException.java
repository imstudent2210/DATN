package com.graduate.touslestemp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
* @File:  BadRequestException.java com.graduate.touslestemp.exception
*
* @Author: TamNLT
* @Since: 20/6/2023 11:26 PM
* @Last update: 20/6/2023
*
* */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 752858877580882829L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}