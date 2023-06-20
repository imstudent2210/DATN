package com.graduate.touslestemp.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Date;
/*
* @File:  TimeKeeping.java com.graduate.touslestemp.domain.entity
*
* @Author: TamNLT
* @Since: 20/6/2023 11:23 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="TimeKeeping")
public class TimeKeeping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="timekeeping_id")
    private Long id;
    @NotNull(message = "Enter month time keeping !")
    private int month;
    @NotNull(message = "Enter number of shift !")
    private int numOfShift;
    @ManyToOne
    @JoinColumn(name = "Salary", referencedColumnName = "salary_id")
    private Salary salary;
    @ManyToOne
    @JoinColumn(name = "Staff", referencedColumnName = "id")
    private Staff Staff;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    protected Date createdDate;
}
