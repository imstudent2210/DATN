package com.graduate.touslestemp.domain.dto;
/*
* @File:  SocialProvider.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:21 PM
* @Last update: 20/6/2023
*
* */
public enum SocialProvider {

	FACEBOOK("facebook"), GITHUB("github"), LOCAL("local");

	private String providerType;

	public String getProviderType() {
		return providerType;
	}

	SocialProvider(final String providerType) {
		this.providerType = providerType;
	}

}
