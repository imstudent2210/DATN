package com.graduate.touslestemp.service;


import com.graduate.touslestemp.domain.dto.LocalUser;
import com.graduate.touslestemp.domain.dto.SignUpRequest;
import com.graduate.touslestemp.domain.entity.User;
import com.graduate.touslestemp.exception.UserAlreadyExistAuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;


public interface UserService {

	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
