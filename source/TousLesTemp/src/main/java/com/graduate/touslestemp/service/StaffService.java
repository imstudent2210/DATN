package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {
    PageResponseDTO<?> getAllStaff(Pageable request);

    Staff find(Long id);

    StaffDTO create(Staff staff) throws Exception;

    void delete(Long id);

    StaffDTO update(StaffDTO staffDto, Long id) throws Exception;

    List<StaffDTO> search(String keyword);

    List<StaffDTO> filter(Long id);

}
