package com.graduate.touslestemp.domain.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

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
