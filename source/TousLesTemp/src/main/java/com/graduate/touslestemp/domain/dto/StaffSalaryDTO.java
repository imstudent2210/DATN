package com.graduate.touslestemp.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @File: StaffSalaryDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:18 AM
 * @Update: 21/6/2023
 */
@Getter
@Setter
public class StaffSalaryDTO {
    private String name;
    private double salary;

    public StaffSalaryDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}