package com.graduate.touslestemp.exception;

/**
 * @File: RequestSuccess.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:24 AM
 * @Update: 21/6/2023
 */
public class RequestSuccess extends RuntimeException {
    public RequestSuccess(String message) {
        super(message);
    }
}
