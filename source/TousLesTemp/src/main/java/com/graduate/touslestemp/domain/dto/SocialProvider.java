package com.graduate.touslestemp.domain.dto;

/**
 * @author Chinna
 * @since 26/3/18
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
