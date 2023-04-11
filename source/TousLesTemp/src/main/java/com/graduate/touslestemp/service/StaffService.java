package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffDto;
import com.graduate.touslestemp.domain.entity.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    PageResponseDTO<?> getAllStaff(Pageable request);
    StaffDto find(Long id);
    StaffDto create(Staff staff) throws Exception;
    void delete (Long id);
    StaffDto update(StaffDto staffDto, Long id) throws Exception;
    List<StaffDto> search(String keyword);
    List<StaffDto> filter(Long id);
}
