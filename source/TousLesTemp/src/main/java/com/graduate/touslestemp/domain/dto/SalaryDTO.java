package com.graduate.touslestemp.domain.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @File: SalaryDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:13 AM
 * @Update: 21/6/2023
 */
@Getter
@Setter
public class SalaryDTO {
    @Id
    private Long id;
    private String name;
    private int basicSalary;

}
