package com.graduate.touslestemp.service.impl;


import com.graduate.touslestemp.domain.dto.LocalUser;
import com.graduate.touslestemp.domain.dto.SignUpRequest;
import com.graduate.touslestemp.domain.dto.SocialProvider;
import com.graduate.touslestemp.domain.entity.Role;
import com.graduate.touslestemp.domain.entity.User;
import com.graduate.touslestemp.domain.repository.RoleRepository;
import com.graduate.touslestemp.domain.repository.UserRepository;
import com.graduate.touslestemp.exception.OAuth2AuthenticationProcessingException;
import com.graduate.touslestemp.exception.UserAlreadyExistAuthenticationException;
import com.graduate.touslestemp.security.oauth2.user.OAuth2UserInfo;
import com.graduate.touslestemp.security.oauth2.user.OAuth2UserInfoFactory;
import com.graduate.touslestemp.service.UserService;
import com.graduate.touslestemp.util.GeneralUtils;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @File: UserServiceImpl.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:28 AM
 * @Update: 21/6/2023
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private SecretGenerator secretGenerator = new DefaultSecretGenerator();
    /**
     * Registers a new admin user based on the provided sign-up request.
     *
     * @param signUpRequest the sign-up request containing the details of the new admin user
     * @return the newly registered admin user
     * @throws UserAlreadyExistAuthenticationException if the user with the given ID or email already exists
     */
    @Override
    @Transactional(value = "transactionManager")
    public User registerNewAdmin(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest);
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user = userRepository.save(user);
        userRepository.flush();
        return user;
    }

    private User buildUser(final SignUpRequest formDTO) {
        User user = new User();
        user.setDisplayName(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
//		roles.add(roleRepository.findByName(Role.ROLE_USER));
        roles.add(roleRepository.findByName(Role.ROLE_ADMIN));
        user.setRoles(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setEnabled(true);
        user.setProviderUserId(formDTO.getProviderUserId());
        if (formDTO.isUsing2FA()) {
            user.setUsing2FA(true);
            user.setSecret(secretGenerator.generate());
        }
        return user;
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }
    /**
     * Processes user registration based on the provided registration ID, attributes, ID token, and user info.
     *
     * @param registrationId the registration ID representing the OAuth2 provider
     * @param attributes     the attributes received from the OAuth2 provider
     * @param idToken        the ID token received from the OAuth2 provider
     * @param userInfo       the user info received from the OAuth2 provider
     * @return the registered local user
     * @throws OAuth2AuthenticationProcessingException if required information (name or email) is not found from the OAuth2 provider, or if there is an authentication processing error
     */
    @Override
    @Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewAdmin(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }
    /**
     * Updates the existing user with the provided OAuth2 user info.
     *
     * @param existingUser    the existing user to be updated
     * @param oAuth2UserInfo the OAuth2 user info
     * @return the updated user
     */
    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }
    /**
     * Converts the OAuth2 user info to a SignUpRequest object.
     *
     * @param registrationId  the registration ID representing the OAuth2 provider
     * @param oAuth2UserInfo the OAuth2 user info
     * @return the SignUpRequest object
     */
    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
