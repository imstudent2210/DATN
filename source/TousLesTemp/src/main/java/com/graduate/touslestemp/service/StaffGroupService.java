package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.StaffPerGroupDTO;
import com.graduate.touslestemp.domain.dto.StaffSalaryDTO;
import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.StaffGroup;

import java.util.List;

public interface StaffGroupService {
    List<StaffGroup> findAll();

    StaffGroup save(StaffGroup category) throws Exception;

    void deleteStaffGroup(Long id);

    StaffGroup findStaffGroup(Long id);

    StaffGroup update(StaffGroup category, Long id) throws Exception;
}
