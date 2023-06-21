package com.graduate.touslestemp.domain.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * @File: TimeKeepingDTO.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:18 AM
 * @Update: 21/6/2023
 */
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
