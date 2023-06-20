package com.graduate.touslestemp.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
/*
* @File:  LoginRequest.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:17 PM
* @Last update: 20/6/2023
*
* */
@Data
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;
}