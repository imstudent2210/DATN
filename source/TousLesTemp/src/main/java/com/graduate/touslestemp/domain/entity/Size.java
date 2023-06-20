package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
/*
* @File:  Size.java com.graduate.touslestemp.domain.entity
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
@Entity
@Data
@Table(name = "size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long id;
    @NotEmpty(message = "Enter size !")
    private String size;
}
