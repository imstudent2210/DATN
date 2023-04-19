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


    //==================

    @Override
    public Staff create2(Staff staff, MultipartFile[] img) throws Exception {
        try {
            Set<StaffImage> images = imageUpload(img);
            staff.setImages(images);
            return this.staffRepository.save(staff);
        } catch (Exception e) {
            e.getMessage();
            throw new RequestException("Không thể thêm nhân viên");
        }
    }

    @Override
    public Staff update2(Staff staff, Long id, MultipartFile[] img) throws Exception {
        try {
            Set<StaffImage> images = imageUpload(img);
            Staff local = this.staffRepository.findById(id)
                    .orElseThrow(() -> new RequestException("Không tồn tại nhân viên này : " + id));

            local.setImages(images);
            StaffDto a = staffMapper.toStaffDTO(staff);
            staffMapper.updateEntity(a, local);
            return this.staffRepository.save(local);
        } catch (Exception e) {
            e.getMessage();
            throw new RequestException("Không thể cập nhật");
        }
    }
    public Set<StaffImage> imageUpload(MultipartFile[] multipartFiles) throws IOException {
        Set<StaffImage> images = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            StaffImage image = new StaffImage(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }
}
