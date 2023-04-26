package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.StaffGroup;
import com.graduate.touslestemp.domain.repository.StaffGroupRepository;
import com.graduate.touslestemp.service.StaffGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/staffgroup")
public class StaffGroupController {
    @Autowired
    private StaffGroupRepository staffGroupRepository;
    @Autowired
    private StaffGroupService staffGroupService;

    @GetMapping("/get")
    List<StaffGroup> allCategory() {
        return this.staffGroupRepository.findAll();
    }
    @GetMapping("/getActivated")
    List<StaffGroup> findCategoryActivated() {
        return this.staffGroupRepository.findStaffGroupActivated();
    }

    @PostMapping("/create")
    public StaffGroup createStaffGroup(@RequestBody @Valid StaffGroup staffGroup) throws Exception {
        staffGroup.setName(staffGroup.getName());
        return this.staffGroupService.save(staffGroup);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StaffGroup> getStaffGroupById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(staffGroupService.findStaffGroup(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public StaffGroup updateStaffGroup(@RequestBody @Valid StaffGroup staffGroup, @PathVariable("id") Long id) throws Exception {
        return this.staffGroupService.update(staffGroup, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStaffGroup(@PathVariable("id") Long id) {
        this.staffGroupService.deleteStaffGroup(id);
    }

}