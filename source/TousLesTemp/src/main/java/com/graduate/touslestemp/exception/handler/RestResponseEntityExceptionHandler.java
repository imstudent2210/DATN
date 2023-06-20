package com.graduate.touslestemp.exception.handler;

import com.graduate.touslestemp.domain.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;
/*
* @File:  RestResponseEntityExceptionHandler.java com.graduate.touslestemp.exception.handler
*
* @Author: TamNLT
* @Since: 20/6/2023 11:26 PM
* @Last update: 20/6/2023
*
* */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logger.error("400 Status Code", ex);
		final BindingResult result = ex.getBindingResult();

		String error = result.getAllErrors().stream().map(e -> {
			if (e instanceof FieldError) {
				return ((FieldError) e).getField() + " : " + e.getDefaultMessage();
			} else {
				return e.getObjectName() + " : " + e.getDefaultMessage();
			}
		}).collect(Collectors.joining(", "));
		return handleExceptionInternal(ex, new ApiResponse(false, error), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
