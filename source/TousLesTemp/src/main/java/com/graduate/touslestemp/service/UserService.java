package com.graduate.touslestemp.service;


import com.graduate.touslestemp.domain.dto.LocalUser;
import com.graduate.touslestemp.domain.dto.SignUpRequest;
import com.graduate.touslestemp.domain.entity.User;
import com.graduate.touslestemp.exception.UserAlreadyExistAuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;
import java.util.Optional;

/**
 * @File: UserService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:47 AM
 * @Update: 21/6/2023
 */
public interface UserService {
    /**
     * Registers a new admin user based on the provided sign-up request.
     *
     * @param signUpRequest the sign-up request containing user information
     * @return the registered User object representing the new admin
     * @throws UserAlreadyExistAuthenticationException if a user with the same email already exists
     */
    public User registerNewAdmin(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;
    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return the User object matching the provided email, or null if not found
     */
    User findUserByEmail(String email);
    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional<User> object containing the user if found, or empty if not found
     */
    Optional<User> findUserById(Long id);
    /**
     * Processes user registration based on the provided registration ID, attributes, ID token, and user info.
     *
     * @param registrationId the registration ID associated with the user registration
     * @param attributes the attributes associated with the user registration
     * @param idToken the ID token associated with the user registration
     * @param userInfo the user info associated with the user registration
     * @return the LocalUser object representing the processed user registration
     */
    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
