package com.graduate.touslestemp.domain.dto;

import lombok.Value;

/**
 * @File: ApiResponse.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:12 AM
 * @Update: 21/6/2023
 */
@Value
public class ApiResponse {
	private Boolean success;
	private String message;
}