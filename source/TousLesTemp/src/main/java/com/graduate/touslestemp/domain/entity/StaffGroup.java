package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
* @File:  StaffGroup.java com.graduate.touslestemp.domain.entity
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
@Table(name="StaffGroup")
public class StaffGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="staff_group_id")
    private Long id;
    @NotEmpty(message = "Enter staff group name !")
    private String name;
    private boolean isActivated = true;


}
