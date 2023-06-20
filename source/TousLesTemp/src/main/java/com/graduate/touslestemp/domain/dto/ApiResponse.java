package com.graduate.touslestemp.domain.dto;

import lombok.Value;
/*
* @File:  ApiResponse.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:13 PM
* @Last update: 20/6/2023
*
* */
@Value
public class ApiResponse {
	private Boolean success;
	private String message;
}