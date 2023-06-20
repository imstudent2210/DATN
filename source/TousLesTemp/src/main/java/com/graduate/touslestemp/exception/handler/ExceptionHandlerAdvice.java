package com.graduate.touslestemp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.graduate.touslestemp.exception.ForeignKeyConstraintException;
import org.springframework.http.ResponseEntity;
/*
* @File:  ExceptionHandlerAdvice.java com.graduate.touslestemp.exception.handler
*
* @Author: TamNLT
* @Since: 20/6/2023 11:25 PM
* @Last update: 20/6/2023
*
* */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ForeignKeyConstraintException.class)
    public ResponseEntity<String> handleForeignKeyConstraintException(ForeignKeyConstraintException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
