package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.Store;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    StaffDTO toStaffDTO(Staff staff);

    Staff toStaffEntity(StaffDTO staffDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StaffDTO staffDto, @MappingTarget Staff staff);
    Store toStoreEntity(StoreDTO storeDto);

    @AfterMapping
    default void updateStore(@MappingTarget Staff staff, StaffDTO staffDto) {
        Store store = toStoreEntity(staffDto.getStore());
        staff.setStore(store);

    }

    List<StaffDTO> toStaffDTOs(List<Staff> staff);
}
