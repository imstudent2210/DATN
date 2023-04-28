package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaryMapper {
    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    SalaryDTO toSalaryDTO(Salary salary);

    Salary toSalaryEntity(SalaryDTO salaryDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(SalaryDTO salaryDTO, @MappingTarget Salary salary);

    List<SalaryDTO> toSalaryDTOs(List<Salary> salaryList);
}
