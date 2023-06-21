package com.graduate.touslestemp.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @File: StaffPerGroupDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:18 AM
 * @Update: 21/6/2023
 */
@Getter
@Setter
public class StaffPerGroupDTO {
    private String name;
    private Long quantity;

    public StaffPerGroupDTO(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}