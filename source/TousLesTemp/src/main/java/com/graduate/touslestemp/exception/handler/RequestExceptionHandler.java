package com.graduate.touslestemp.exception.handler;

import com.graduate.touslestemp.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
/*
* @File:  RequestExceptionHandler.java com.graduate.touslestemp.exception.handler
*
* @Author: TamNLT
* @Since: 20/6/2023 11:25 PM
* @Last update: 20/6/2023
*
* */
@ControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(value = {RequestException.class} )
    public ResponseEntity<Object> handlerApiRequestException(RequestException e){

        Exception exception = new Exception(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

    return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
    }
}
