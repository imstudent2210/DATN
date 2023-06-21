package com.graduate.touslestemp.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * @File: CurrentUser.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:09 AM
 * @Update: 21/6/2023
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
