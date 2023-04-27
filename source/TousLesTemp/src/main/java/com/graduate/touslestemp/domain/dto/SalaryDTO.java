package com.graduate.touslestemp.domain.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryDTO {
    @Id
    private Long id;
    private String name;
    private int basicSalary;

}
