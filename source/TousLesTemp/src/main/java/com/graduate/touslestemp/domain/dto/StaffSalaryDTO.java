package com.graduate.touslestemp.domain.dto;

import lombok.Getter;
import lombok.Setter;
/*
* @File:  StaffSalaryDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:21 PM
* @Last update: 20/6/2023
*
* */
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