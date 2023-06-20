package com.graduate.touslestemp.domain.dto;

import lombok.Value;
import java.util.List;
/*
* @File:  UserInfo.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:17 PM
* @Last update: 20/6/2023
*
* */

@Value
public class UserInfo {
	private String id, displayName, email;
	private List<String> roles;
}