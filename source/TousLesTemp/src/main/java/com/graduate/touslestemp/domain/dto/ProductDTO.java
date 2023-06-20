package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Size;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/*
* @File:  ProductDTO.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:17 PM
* @Last update: 20/6/2023
*
* */
@Getter
@Setter
public class ProductDTO {
    @Id
    private Long id;
    private String description;
    private Integer inventory;
    private String name;
    private Double price;
    Category category;
    Size size;
    StoreDTO store;
    private String image;

}
