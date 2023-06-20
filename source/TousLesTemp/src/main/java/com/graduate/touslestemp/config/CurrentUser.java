package com.graduate.touslestemp.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/*
* @File:  CurrentUser.java com.graduate.touslestemp.config
*
* @Author: TamNLT
* @Since: 20/6/2023 11:08 PM
* @Last update: 20/6/2023
*
* */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
