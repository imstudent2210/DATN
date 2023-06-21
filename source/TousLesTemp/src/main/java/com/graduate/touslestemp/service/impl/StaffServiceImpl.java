package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.mapper.StaffMapper;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @File: StaffServiceImpl.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:28 AM
 * @Update: 21/6/2023
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffMapper staffMapper;

    @Override
    public PageResponseDTO<?> getAllStaff(Pageable request) {
        return null;
    }

    @Override
    public Staff find(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty()) {
            throw new RequestException("Not found this staff, id: " + id);
        }
        return staffRepository.findById(id).get();
    }

    @Override
    public StaffDTO create(Staff staff) throws Exception {
        if (isExisStaff(staff.getName())) {
            System.out.println("This staff has already");
            throw new RequestException("This staff has already!");
        } else {
            return (staffMapper.toStaffDTO(staffRepository.save(staff)));
        }
    }

    @Override
    public void delete(Long id) {
        staffRepository.delete(staffRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this staff: " + id)));
    }

    @Override
    public StaffDTO update(StaffDTO staffDto, Long id) throws Exception {
        Staff local = this.staffRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this staff: " + id));
        staffMapper.updateEntity(staffDto, local);
        return (staffMapper.toStaffDTO(staffRepository.save(local)));
    }

    @Override
    public List<StaffDTO> search(String name) {

        List<Staff> staffs = this.staffRepository.searchStaffByName("%" + name + "%");
        if (staffs.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StaffDTO> staffDTOS = this.staffMapper.toStaffDTOs(staffs);
            return staffDTOS;
        }
    }

    @Override
    public List<StaffDTO> filter(Long id) {

        List<Staff> staffs = this.staffRepository.filterStaffByStoreId(id);
        if (staffs.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StaffDTO> staffDTOS = this.staffMapper.toStaffDTOs(staffs);
            return staffDTOS;
        }
    }

    public boolean isExisStaff(String name) {
        Staff checkStaff = staffRepository.findStaffByName(name);
        if (checkStaff != null) {
            return true;
        }
        return false;
    }

}
