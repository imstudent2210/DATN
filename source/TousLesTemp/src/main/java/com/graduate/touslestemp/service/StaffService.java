package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffDto;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StaffService {
    PageResponseDTO<?> getAllStaff(Pageable request);
    Staff find(Long id);
    StaffDto create(Staff staff) throws Exception;
    void delete (Long id);
    StaffDto update(StaffDto staffDto, Long id) throws Exception;
    List<StaffDto> search(String keyword);
    List<StaffDto> filter(Long id);

    //================
    Staff create2(Staff staff, MultipartFile[] img) throws Exception;
    Staff update2(Staff product, Long id,MultipartFile [] img ) throws Exception;

}
