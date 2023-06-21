package com.graduate.touslestemp.domain.dto;

import lombok.Value;
import java.util.List;

/**
 * @File: UserInfo.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:18 AM
 * @Update: 21/6/2023
 */
@Value
public class UserInfo {
	private String id, displayName, email;
	private List<String> roles;
}