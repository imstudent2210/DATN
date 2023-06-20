package com.graduate.touslestemp.domain.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/*
* @File:  SalaryDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:17 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
public class SalaryDTO {
    @Id
    private Long id;
    private String name;
    private int basicSalary;

}
