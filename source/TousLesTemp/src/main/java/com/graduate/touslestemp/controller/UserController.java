package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.config.CurrentUser;
import com.graduate.touslestemp.domain.dto.LocalUser;
import com.graduate.touslestemp.util.GeneralUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File: UserController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:12 AM
 * @Update: 21/6/2023
 */
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * Retrieves the current user information for the authenticated user with the role "STOREMANAGER".
     *
     * @param user The LocalUser object representing the current authenticated user
     * @return ResponseEntity containing the user information and HTTP status OK
     */
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('STOREMANAGER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    /**
     * Retrieves the public content.
     *
     * @return ResponseEntity containing the public content message and HTTP status OK
     */
    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    /**
     * Retrieves the user content for the authenticated user with the role "STOREMANAGER".
     *
     * @return ResponseEntity containing the user content message and HTTP status OK
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('STOREMANAGER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    /**
     * Retrieves the admin content for the authenticated user with the role "ADMIN".
     *
     * @return ResponseEntity containing the admin content message and HTTP status OK
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    /**
     * Retrieves the current admin user information for the authenticated user with the role "ADMIN".
     *
     * @param user The LocalUser object representing the current authenticated admin user
     * @return ResponseEntity containing the admin user information and HTTP status OK
     */
    @GetMapping("/admin/me")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCurrentAdmin(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }
}