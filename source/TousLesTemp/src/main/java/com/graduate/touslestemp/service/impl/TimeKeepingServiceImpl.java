package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffSalaryDTO;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import com.graduate.touslestemp.domain.mapper.SalaryMapper;
import com.graduate.touslestemp.domain.mapper.StaffMapper;
import com.graduate.touslestemp.domain.mapper.TimeKeepingMapper;
import com.graduate.touslestemp.domain.repository.TimeKeepingRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.TimeKeepingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @File: TimeKeepingServiceImpl.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:28 AM
 * @Update: 21/6/2023
 */
@Service
public class TimeKeepingServiceImpl implements TimeKeepingService {
    @Autowired
    TimeKeepingRepository timeKeepingRepository;
    @Autowired
    TimeKeepingMapper timeKeepingMapper;
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    StaffMapper staffMapper;

    @Override
    public PageResponseDTO<?> getAllTimeKeeping(Pageable request) {
        return null;
    }

    @Override
    public TimeKeeping find(Long id) {
        Optional<TimeKeeping> timeKeeping = timeKeepingRepository.findById(id);
        if (timeKeeping.isEmpty()) {
            throw new RequestException("Not found this time keeping, id: " + id);
        }
        return timeKeepingRepository.findById(id).get();
    }

    @Override
    public TimeKeepingDTO create(TimeKeeping timeKeeping) throws Exception {
        Date now = Calendar.getInstance().getTime();

        timeKeeping.setCreatedDate(now);
        return (timeKeepingMapper.toTimeKeepingDTO(timeKeepingRepository.save(timeKeeping)));
    }

    @Override
    public void delete(Long id) {
        timeKeepingRepository.delete(timeKeepingRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this time keeping: " + id)));
    }

    @Override
    public TimeKeepingDTO update(TimeKeepingDTO timeKeepingDTO, Long id) throws Exception {
        TimeKeeping local = this.timeKeepingRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this time keeping id: " + id));

        timeKeepingMapper.updateEntity(timeKeepingDTO, local);

        TimeKeeping updatedTimeKeeping = timeKeepingRepository.save(local);

        return (timeKeepingMapper.toTimeKeepingDTO(updatedTimeKeeping));
    }

    @Override
    public List<Staff> allStaffHaveTimeKeepingPerMonth(Long month) throws Exception {
        if (!timeKeepingRepository.allTimeKeepingPerMonth(month).isEmpty()) {
            return this.timeKeepingRepository.allStaffHaveTimeKeepingPerMonth(month);
        } else throw new RequestException("No data!");
    }

    @Override
    public List<StaffSalaryDTO> calculateAllStaffSalaryPerMonth(Long month) throws Exception {
        List<StaffSalaryDTO> staffSalaryList = new ArrayList<>();
        List<Staff> staffList = timeKeepingRepository.allStaffHaveTimeKeepingPerMonth(month);
        for (Staff staff : staffList) {
            double salary = timeKeepingRepository.monthSalary(staff.getId(), month);
            StaffSalaryDTO staffSalaryDTO = new StaffSalaryDTO(staff.getName(), salary);
            staffSalaryList.add(staffSalaryDTO);
        }
        return staffSalaryList;
    }

    @Override
    public List<TimeKeepingDTO> search(String keyword) {
        return null;
    }

    @Override
    public List<TimeKeepingDTO> filter(Long id) {
        return null;
    }

}
