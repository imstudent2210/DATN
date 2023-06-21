package com.graduate.touslestemp.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @File: LoginRequest.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:13 AM
 * @Update: 21/6/2023
 */
@Data
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;
}