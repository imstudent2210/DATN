package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toProductDTO(Product product);

    Product toProductEntity(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductDto productDto, @MappingTarget Product product);

    List<ProductDto> toProductDTOs(List<Product> products);
//    void toPageResponeDto(Pageable productDtos, @MappingTarget PageResponseDTO <ProductDto> pageResponseDTO);

}
