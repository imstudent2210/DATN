package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.StaffDto;
import com.graduate.touslestemp.domain.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    StaffDto toStaffDTO(Staff staff);

    Staff toStaffEntity(StaffDto staffDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StaffDto staffDto, @MappingTarget Staff staff);

    List<StaffDto> toStaffDTOs(List<Staff> staff);
}
