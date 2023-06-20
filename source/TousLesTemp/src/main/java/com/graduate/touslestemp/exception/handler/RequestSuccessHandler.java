package com.graduate.touslestemp.exception.handler;

import com.graduate.touslestemp.exception.RequestSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
/*
* @File:  RequestSuccessHandler.java com.graduate.touslestemp.exception.handler
*
* @Author: TamNLT
* @Since: 20/6/2023 11:25 PM
* @Last update: 20/6/2023
*
* */
@ControllerAdvice
public class RequestSuccessHandler {
    @ExceptionHandler(value = {RequestSuccess.class} )
    public ResponseEntity<Object> handlerApiRequestException(RequestSuccess e){

        Exception exception = new Exception(e.getMessage(), HttpStatus.OK, ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

    return new ResponseEntity<>(exception,HttpStatus.OK);
    }
}
