package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.domain.entity.Staff;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TimeKeepingDTO {
    @Id
    private Long id;

    private int month;

    private int numOfShift;

    private Salary salary;

    private Staff staff;
    private Date createdDate;
}
