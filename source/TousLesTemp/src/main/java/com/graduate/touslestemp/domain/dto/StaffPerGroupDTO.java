package com.graduate.touslestemp.domain.dto;

import lombok.Getter;
import lombok.Setter;

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