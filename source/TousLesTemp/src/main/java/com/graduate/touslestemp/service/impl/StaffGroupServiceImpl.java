package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.StaffGroup;
import com.graduate.touslestemp.domain.repository.StaffGroupRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.exception.RequestSuccess;
import com.graduate.touslestemp.service.StaffGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffGroupServiceImpl implements StaffGroupService {
    @Autowired
    private StaffGroupRepository staffGroupRepository;

    @Override
    public List<StaffGroup> findAll() {
       return this.staffGroupRepository.findAll();
    }

    @Override
    public StaffGroup save(StaffGroup staffGroup) throws Exception {
        if (isExisStaffGroup(staffGroup.getName())) {
            System.out.println("This staff group has already");
            throw new RequestException("This staff group has already!");
        } else return this.staffGroupRepository.save(staffGroup);
    }

    @Override
    public void deleteStaffGroup(Long id) {
        StaffGroup staffGroup = new StaffGroup();
        staffGroup.setId(id);
        Optional<StaffGroup> local = this.staffGroupRepository.findById(staffGroup.getId());
        if (!local.isPresent()) {
            System.out.println("Not found this staff group ");
            throw new RequestException("Not found staff group id: " + id);
        } else {
            staffGroup.setActivated(false);
            staffGroup.setName(local.get().getName());
            staffGroupRepository.save(staffGroup);
            throw new RequestSuccess("Delete staff group id " + id + " completed! ");
        }
    }

    @Override
    public StaffGroup findStaffGroup(Long id) {
        Optional<StaffGroup> staffGroup = staffGroupRepository.findById(id);
        if (staffGroup.isEmpty()) {
            throw new RequestException("Not found group, id: " + id);
        }
        return staffGroupRepository.findById(id).get();
    }

    @Override
    public StaffGroup update(StaffGroup staffGroup, Long id) throws Exception {
        Optional<StaffGroup> local = staffGroupRepository.findById(id);
        if (local.isEmpty()) {
            throw new RequestException("Not found staff group, id: " + id);
        }
        else {
            StaffGroup updateStaffGroup = this.staffGroupRepository.findStaffGroupByName(local.get().getName());
            String updateName = staffGroup.getName();
            StaffGroup local1 = this.staffGroupRepository.findStaffGroupByName(updateName);
            local1.isActivated();
            if (updateStaffGroup == null) {
                System.out.println("Not found this staff group: " + id);
            } else {
                if (local1 == null | (local1 !=null &&  (local1.isActivated()!=staffGroup.isActivated())) ) {
                    updateStaffGroup.setName(staffGroup.getName());
                    updateStaffGroup.setActivated(staffGroup.isActivated());
                }
                else throw new RequestException("This category has already: " + updateName);
            }
            return this.staffGroupRepository.save(updateStaffGroup);
        }
    }
    public boolean isExisStaffGroup(String name) {
        StaffGroup checkStaffGroup = staffGroupRepository.findStaffGroupByName(name);
        if (checkStaffGroup != null) {
            return true;
        }
        return false;
    }


}
