package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffSalaryDTO;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TimeKeepingService {
    PageResponseDTO<?> getAllTimeKeeping(Pageable request);

    TimeKeeping find(Long id);

    TimeKeepingDTO create(TimeKeeping timeKeeping) throws Exception;

    void delete(Long id);

    TimeKeepingDTO update(TimeKeepingDTO timeKeepingDTO, Long id) throws Exception;

    List<TimeKeepingDTO> search(String keyword);

    List<TimeKeepingDTO> filter(Long id);

    List<Staff> allStaffHaveTimeKeepingPerMonth(Long month) throws Exception;

    List<StaffSalaryDTO> calculateAllStaffSalaryPerMonth(Long month) throws Exception;

}
