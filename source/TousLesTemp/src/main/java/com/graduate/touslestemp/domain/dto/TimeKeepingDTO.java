package com.graduate.touslestemp.domain.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
/*
* @File:  TimeKeepingDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:21 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
public class TimeKeepingDTO {
    @Id
    private Long id;

    private int month;

    private int numOfShift;

    private SalaryDTO salary;

    private StaffDTO Staff;
    private Date createdDate;
}
