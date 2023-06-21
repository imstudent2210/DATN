package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.StaffGroup;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @File: StaffDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:17 AM
 * @Update: 21/6/2023
 */
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
