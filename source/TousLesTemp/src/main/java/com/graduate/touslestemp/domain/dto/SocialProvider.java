package com.graduate.touslestemp.domain.dto;

/**
 * @File: SocialProvider.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:17 AM
 * @Update: 21/6/2023
 */
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
