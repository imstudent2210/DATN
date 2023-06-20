package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
/*
* @File:  StoreMapper.java com.graduate.touslestemp.domain.mapper
*
* @Author: TamNLT
* @Since: 20/6/2023 11:23 PM
* @Last update: 20/6/2023
*
* */
@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    StoreDTO toStoreDTO(Store store);

    Store toStoreEntity(StoreDTO storeDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StoreDTO storeDto, @MappingTarget Store store);

    List<StoreDTO> toStoreDTOs(List<Store> stores);


}
