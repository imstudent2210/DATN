package com.graduate.touslestemp.domain.mapper;

import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TimeKeepingMapper {
    TimeKeepingMapper INSTANCE = Mappers.getMapper(TimeKeepingMapper.class);

    TimeKeepingDTO toTimeKeepingDTO(TimeKeeping timeKeeping);

    TimeKeeping toTimeKeepingEntity(TimeKeepingDTO timeKeepingDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntity(TimeKeepingDTO timeKeepingDTO, @MappingTarget TimeKeeping timeKeeping);

    List<TimeKeepingDTO> toTimeKeepingDTOs(List<TimeKeeping> timeKeepingList);
}
