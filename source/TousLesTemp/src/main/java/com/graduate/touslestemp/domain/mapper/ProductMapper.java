package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.ProductDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
/*
* @File:  ProductMapper.java com.graduate.touslestemp.domain.mapper
*
* @Author: TamNLT
* @Since: 20/6/2023 11:23 PM
* @Last update: 20/6/2023
*
* */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product);

    Product toProductEntity(ProductDTO productDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductDTO productDto, @MappingTarget Product product);

    Store toStoreEntity(StoreDTO storeDto);

    @AfterMapping
    default void updateStore(@MappingTarget Product product, ProductDTO productDto) {
        Store store = toStoreEntity(productDto.getStore());
        product.setStore(store);

    }

    List<ProductDTO> toProductDTOs(List<Product> products);

}
