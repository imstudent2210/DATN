package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.dto.StaffDto;
import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.*;
import com.graduate.touslestemp.domain.mapper.StaffMapper;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            throw new RequestException("Không tồn tại nhân viên, id: " + id);
        }
        return staffRepository.findById(id).get();
    }

    @Override
    public StaffDto create(Staff staff) throws Exception {
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
                .orElseThrow(()->new RequestException("Không tồn tại nhân viên: "+id)));
    }

    @Override
    public StaffDto update(StaffDto staffDto, Long id) throws Exception {
        Staff local = this.staffRepository.findById(id)
                .orElseThrow(() -> new RequestException("Không tồn tại nhân viên: " + id));
        staffMapper.updateEntity(staffDto, local);
        return (staffMapper.toStaffDTO(staffRepository.save(local)));
    }

    @Override
    public List<StaffDto> search(String name) {

        List<Staff> staffs = this.staffRepository.searchStaffByName("%" + name + "%");
        if (staffs.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StaffDto> staffDtos = this.staffMapper.toStaffDTOs(staffs);
            return staffDtos;
        }
    }

    @Override
    public List<StaffDto> filter(Long id) {

        List<Staff> staffs = this.staffRepository.filterStaffByStoreId(id);
        if (staffs.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StaffDto> staffDtos = this.staffMapper.toStaffDTOs(staffs);
            return staffDtos;
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
