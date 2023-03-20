package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Address;

public record StoreDTO(String name, String email, String phone, Address address) {}
