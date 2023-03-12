package com.graduate.touslestemp.exception;

public class AdminAlreadyExistsException extends RuntimeException {
    public AdminAlreadyExistsException(String username) {
        super(username + " already exists");
    }
}