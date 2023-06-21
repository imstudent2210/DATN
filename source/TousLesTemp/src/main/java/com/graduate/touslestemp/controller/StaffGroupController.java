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

/**
 * @File: StaffGroupController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:12 AM
 * @Update: 21/6/2023
 */
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

    /**
     * Retrieves all staff groups.
     *
     * @return List of StaffGroup objects representing all staff groups
     */
    @GetMapping("/get")
    List<StaffGroup> allCategory() {
        return this.staffGroupRepository.findAll();
    }
    /**
     * Retrieves all activated staff groups.
     *
     * @return List of StaffGroup objects representing activated staff groups
     */
    @GetMapping("/getActivated")
    List<StaffGroup> findCategoryActivated() {
        return this.staffGroupRepository.findStaffGroupActivated();
    }
    /**
     * Creates a new staff group.
     *
     * @param staffGroup The StaffGroup object representing the new staff group
     * @return The created StaffGroup object
     * @throws Exception if an error occurs during creation
     */
    @PostMapping("/create")
    public StaffGroup createStaffGroup(@RequestBody @Valid StaffGroup staffGroup) throws Exception {
        staffGroup.setName(staffGroup.getName());
        return this.staffGroupService.save(staffGroup);
    }
    /**
     * Retrieves a specific staff group by its ID.
     *
     * @param id The ID of the staff group to retrieve
     * @return ResponseEntity containing the StaffGroup object and HTTP status OK
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<StaffGroup> getStaffGroupById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(staffGroupService.findStaffGroup(id), HttpStatus.OK);
    }
    /**
     * Updates an existing staff group with the given ID.
     *
     * @param staffGroup The updated StaffGroup object
     * @param id         The ID of the staff group to update
     * @return The updated StaffGroup object
     * @throws Exception if an error occurs during update
     */
    @PutMapping("/update/{id}")
    public StaffGroup updateStaffGroup(@RequestBody @Valid StaffGroup staffGroup, @PathVariable("id") Long id) throws Exception {
        return this.staffGroupService.update(staffGroup, id);
    }
    /**
     * Deletes a staff group with the given ID.
     *
     * @param id The ID of the staff group to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteStaffGroup(@PathVariable("id") Long id) {
        this.staffGroupService.deleteStaffGroup(id);
    }
    /**
     * Retrieves the count of staff members in each staff group.
     *
     * @return ResponseEntity containing a list of StaffPerGroupDTO objects representing the count of staff members per group
     */
    @GetMapping("/get/countStaffByGroup")
    public ResponseEntity<List<StaffPerGroupDTO>> countStaffByGroup() {
        Query query = entityManager.createQuery("select s.staffGroup.name, count (s) from Staff s group by s.staffGroup");
        List<StaffPerGroupDTO> results = query.getResultList();
        return ResponseEntity.ok(results);
    }
}