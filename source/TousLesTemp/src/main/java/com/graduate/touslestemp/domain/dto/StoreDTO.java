package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Address;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/*
* @File:  StoreDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:21 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
public class StoreDTO {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String addressDetail;
    Address address;
    private String image;
}
