package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @File: StaffGroup.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:20 AM
 * @Update: 21/6/2023
 */
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
