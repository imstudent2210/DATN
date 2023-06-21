package com.graduate.touslestemp.util;


import com.graduate.touslestemp.domain.dto.LocalUser;
import com.graduate.touslestemp.domain.dto.SocialProvider;
import com.graduate.touslestemp.domain.dto.UserInfo;
import com.graduate.touslestemp.domain.entity.Role;
import com.graduate.touslestemp.domain.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @File: GeneralUtils.java
 * @Author: TamNLT
 * @Since: 21/6/2023 8:26 PM
 * @Update: 21/6/2023
 */
public class GeneralUtils {
    /**
     * Builds a list of SimpleGrantedAuthority objects from the given set of roles.
     *
     * @param roles The set of roles.
     * @return The list of SimpleGrantedAuthority objects.
     */
    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * Converts the provider ID to the corresponding SocialProvider enum value.
     *
     * @param providerId The provider ID.
     * @return The SocialProvider enum value.
     */
    public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }

    /**
     * Builds a UserInfo object from the given LocalUser object.
     *
     * @param localUser The LocalUser object.
     * @return The UserInfo object.
     */
    public static UserInfo buildUserInfo(LocalUser localUser) {
        List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        User user = localUser.getUser();
        return new UserInfo(user.getId().toString(), user.getDisplayName(), user.getEmail(), roles);
    }
}
