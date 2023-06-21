package com.graduate.touslestemp.domain.dto;

import lombok.Value;

/**
 * @File: JwtAuthenticationResponse.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:13 AM
 * @Update: 21/6/2023
 */
@Value
public class JwtAuthenticationResponse {
	private String accessToken;
	private boolean authenticated;
	private UserInfo user;
}