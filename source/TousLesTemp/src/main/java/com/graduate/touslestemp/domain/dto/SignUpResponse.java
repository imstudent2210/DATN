package com.graduate.touslestemp.domain.dto;

import lombok.Value;

/**
 * @File: SignUpResponse.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:13 AM
 * @Update: 21/6/2023
 */
@Value
public class SignUpResponse {
	private boolean using2FA;
	private String qrCodeImage;
}