package com.graduate.touslestemp.exception;

/**
 * @File: RequestException.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:24 AM
 * @Update: 21/6/2023
 */
public class RequestException extends RuntimeException {
    public RequestException(String message) {
        super(message);
    }
}
