package com.graduate.touslestemp.domain.dto;

import lombok.Value;
/*
* @File:  SignUpResponse.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:21 PM
* @Last update: 20/6/2023
*
* */
@Value
public class SignUpResponse {
	private boolean using2FA;
	private String qrCodeImage;
}