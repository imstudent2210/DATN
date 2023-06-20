package com.graduate.touslestemp.domain.dto;

import lombok.Value;
/*
* @File:  JwtAuthenticationResponse.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:12 PM
* @Last update: 20/6/2023
*
* */

@Value
public class JwtAuthenticationResponse {
	private String accessToken;
	private boolean authenticated;
	private UserInfo user;
}