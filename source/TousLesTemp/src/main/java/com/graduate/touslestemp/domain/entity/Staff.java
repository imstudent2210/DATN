package com.graduate.touslestemp.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
/*
* @File:  Staff.java com.graduate.touslestemp.domain.entity
*
* @Author: TamNLT
* @Since: 20/6/2023 11:22 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotEmpty(message = "Enter staff name !")
    private String name;
    @NotEmpty(message = "Enter staff email !")
    private String email;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "StaffGroup", referencedColumnName = "staff_group_id")
    private StaffGroup staffGroup;

    @ManyToOne
    @JoinColumn(name = "Store", referencedColumnName = "store_id")
    private Store store;
    private String image;
    @OneToMany(mappedBy = "Staff", cascade = CascadeType.REMOVE)
    private List<TimeKeeping> timeKeepingList;

}
