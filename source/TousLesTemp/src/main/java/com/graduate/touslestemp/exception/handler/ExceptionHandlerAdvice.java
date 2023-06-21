package com.graduate.touslestemp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.graduate.touslestemp.exception.ForeignKeyConstraintException;
import org.springframework.http.ResponseEntity;

/**
 * @File: ExceptionHandlerAdvice.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:23 AM
 * @Update: 21/6/2023
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ForeignKeyConstraintException.class)
    public ResponseEntity<String> handleForeignKeyConstraintException(ForeignKeyConstraintException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
