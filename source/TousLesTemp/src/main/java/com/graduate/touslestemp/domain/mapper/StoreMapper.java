package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
//    @Mapping(source = "store.address", target = "address")
    StoreDto toStoreDTO (Store store);
//    @Mapping(source = "storeDto.address", target = "address")
    Store toStoreEntity(StoreDto storeDTO);


    List<StoreDto> toStoreDTOs(List<Store> stores);




}
