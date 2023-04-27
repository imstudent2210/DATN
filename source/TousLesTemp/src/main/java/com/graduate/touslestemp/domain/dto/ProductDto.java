package com.graduate.touslestemp.domain.dto;


import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Size;
import com.graduate.touslestemp.domain.entity.Store;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @Id
    private Long id;
    private String description;
    private Integer inventory;
    private String name;
    private Double price;
    Category category;
    Size size;
    StoreDto store;
    private String image;

}
