package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.StaffGroup;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/*
* @File:  StaffDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:21 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
public class StaffDTO {
    @Id
    private Long id;
    private String email;
    private String name;
    private String phone;
    private StaffGroup staffGroup;
    private StoreDTO store;
    private String image;
}
