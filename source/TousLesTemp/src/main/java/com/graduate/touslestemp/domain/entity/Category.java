package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
/*
* @File:  Category.java com.graduate.touslestemp.domain.entity
*
* @Author: TamNLT
* @Since: 20/6/2023 11:22 PM
* @Last update: 20/6/2023
*
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @NotEmpty(message = "Enter category !")
    private String name;
    private boolean isActivated = true;

}
