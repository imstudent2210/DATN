package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    StoreDto toStoreDTO(Store store);

    Store toStoreEntity(StoreDto storeDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StoreDto storeDto, @MappingTarget Store store);

    List<StoreDto> toStoreDTOs(List<Store> stores);


}
