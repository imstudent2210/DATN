package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Address;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @File: StoreDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:18 AM
 * @Update: 21/6/2023
 */
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
