package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
/*
* @File:  TimeKeepingMapper.java com.graduate.touslestemp.domain.mapper
*
* @Author: TamNLT
* @Since: 20/6/2023 11:23 PM
* @Last update: 20/6/2023
*
* */
@Mapper(componentModel = "spring")
public interface TimeKeepingMapper {
    TimeKeepingMapper INSTANCE = Mappers.getMapper(TimeKeepingMapper.class);

    TimeKeepingDTO toTimeKeepingDTO(TimeKeeping timeKeeping);

    TimeKeeping toTimeKeepingEntity(TimeKeepingDTO timeKeepingDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(TimeKeepingDTO timeKeepingDTO, @MappingTarget TimeKeeping timeKeeping);

    Staff toStaffEntity(StaffDTO staffDTO);

    Salary toSalaryEntity(SalaryDTO salaryDTO);

    @AfterMapping
    default void updateStaffAndSalary(@MappingTarget TimeKeeping timeKeeping, TimeKeepingDTO timeKeepingDTO) {
        Staff staff = toStaffEntity(timeKeepingDTO.getStaff());
        timeKeeping.setStaff(staff);

        Salary salary = toSalaryEntity(timeKeepingDTO.getSalary());
        timeKeeping.setSalary(salary);
    }

    List<TimeKeepingDTO> toTimeKeepingDTOs(List<TimeKeeping> timeKeepingList);
}
