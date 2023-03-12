package com.graduate.touslestemp.exception;

public class NotFoundAdminException extends RuntimeException {
    public NotFoundAdminException(String username) {
        super("Not found this account " );
    }
}