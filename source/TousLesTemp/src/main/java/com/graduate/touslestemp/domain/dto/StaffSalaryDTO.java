package com.graduate.touslestemp.domain.dto;

import lombok.Getter;
import lombok.Setter;

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