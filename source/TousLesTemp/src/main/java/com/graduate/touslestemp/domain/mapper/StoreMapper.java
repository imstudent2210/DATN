package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
//    @Mapping(source = "store.id", ignore = true, target = "id")
    StoreDto toStoreDTO (Store store);
//    @Mapping(source = "storeDto.address", target = "address")
    Store toStoreEntity(StoreDto storeDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StoreDto storeDto, @MappingTarget Store store);

    List<StoreDto> toStoreDTOs(List<Store> stores);




}
