package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.StaffPerGroupDTO;
import com.graduate.touslestemp.domain.entity.StaffGroup;
import com.graduate.touslestemp.domain.repository.StaffGroupRepository;
import com.graduate.touslestemp.service.StaffGroupService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
* @File:  StaffGroupController.java com.graduate.touslestemp.controller
*
* @Author: TamNLT
* @Since: 20/6/2023 11:13 PM
* @Last update: 20/6/2023
*
* */
@RestController
@CrossOrigin("*")
@RequestMapping("/staffgroup")
public class StaffGroupController {
    @Autowired
    private StaffGroupRepository staffGroupRepository;
    @Autowired
    private StaffGroupService staffGroupService;
    @PersistenceContext
    private EntityManager entityManager;

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

    @GetMapping("/get/countStaffByGroup")
    public ResponseEntity<List<StaffPerGroupDTO>> countStaffByGroup() {
        Query query = entityManager.createQuery("select s.staffGroup.name, count (s) from Staff s group by s.staffGroup");
        List<StaffPerGroupDTO> results = query.getResultList();
        return ResponseEntity.ok(results);
    }
}