package com.graduate.touslestemp.domain.dto;


import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Image;
import com.graduate.touslestemp.domain.entity.Size;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductDto {
    @Id
    private Long id;
    private String description;
//    private String image;
    private Integer inventory;
    private String name;
    private Double price;
    Set<Image> image;
    Category category;
    Size size;
    StoreDto store;

}
