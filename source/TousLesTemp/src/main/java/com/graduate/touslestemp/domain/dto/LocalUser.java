package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.util.GeneralUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * @File: LocalUser.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:13 AM
 * @Update: 21/6/2023
 */
public class LocalUser extends User implements OAuth2User, OidcUser {
	private static final long serialVersionUID = -2845160792248762779L;
	private final OidcIdToken idToken;
	private final OidcUserInfo userInfo;
	private Map<String, Object> attributes;
	private com.graduate.touslestemp.domain.entity.User user;

	/**
	 * Constructs a new LocalUser object with the provided parameters.
	 *
	 * @param userID                the user ID
	 * @param password              the password
	 * @param enabled               whether the user is enabled
	 * @param accountNonExpired     whether the user account is non-expired
	 * @param credentialsNonExpired whether the user credentials are non-expired
	 * @param accountNonLocked      whether the user account is non-locked
	 * @param authorities           the authorities/roles assigned to the user
	 * @param user                  the associated User entity
	 */
	public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
			final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final com.graduate.touslestemp.domain.entity.User	 user) {
		this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user, null, null);
	}

	/**
	 * Constructs a new LocalUser object with the provided parameters.
	 *
	 * @param userID                the user ID
	 * @param password              the password
	 * @param enabled               whether the user is enabled
	 * @param accountNonExpired     whether the user account is non-expired
	 * @param credentialsNonExpired whether the user credentials are non-expired
	 * @param accountNonLocked      whether the user account is non-locked
	 * @param authorities           the authorities/roles assigned to the user
	 * @param user                  the associated User entity
	 * @param idToken               the OIDC ID token
	 * @param userInfo              the OIDC user info
	 */
	public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
			final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final com.graduate.touslestemp.domain.entity.User user, OidcIdToken idToken,
			OidcUserInfo userInfo) {
		super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
		this.idToken = idToken;
		this.userInfo = userInfo;
	}

	public static LocalUser create( com.graduate.touslestemp.domain.entity.User user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()),
				user, idToken, userInfo);
		localUser.setAttributes(attributes);
		return localUser;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return this.user.getDisplayName();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Map<String, Object> getClaims() {
		return this.attributes;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return this.userInfo;
	}

	@Override
	public OidcIdToken getIdToken() {
		return this.idToken;
	}

	public com.graduate.touslestemp.domain.entity.User getUser() {
		return user;
	}
}
