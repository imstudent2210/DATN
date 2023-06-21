package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.mapper.StaffMapper;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @File: StaffController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:12 AM
 * @Update: 21/6/2023
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffMapper staffMapper;

    /**
     * Retrieves all staff members as a list of StaffDTO objects.
     *
     * @return ResponseEntity containing the list of StaffDTO objects and HTTP status OK
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<StaffDTO>> getAllStaffDTO() {
        return new ResponseEntity<>(staffMapper.toStaffDTOs(staffRepository.findAll()), HttpStatus.OK);
    }

    /**
     * Retrieves all staff members as a list of Staff objects.
     *
     * @return ResponseEntity containing the list of Staff objects and HTTP status OK
     */
    @GetMapping("/get")
    public ResponseEntity<List<Staff>> allProduct() {
        return new ResponseEntity<>(staffRepository.findAll(), HttpStatus.OK);
    }
    /**
     * Retrieves a specific staff member by their ID.
     *
     * @param id The ID of the staff member to retrieve
     * @return ResponseEntity containing the StaffDTO object and HTTP status OK
     */

    @GetMapping("/get/{id}")
    public ResponseEntity<StaffDTO> getStaffDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(staffMapper.toStaffDTO(staffService.find(id)), HttpStatus.OK);
    }

    /**
     * Creates a new StaffDTO.
     *
     * @param staff The Staff object representing the new staff member
     * @return ResponseEntity containing the created StaffDTO object and HTTP status OK
     * @throws Exception if an error occurs during creation
     */
    @PostMapping("/create")
    public ResponseEntity<StaffDTO> createStaffDTO(@RequestBody @Valid Staff staff) throws Exception {
        return new ResponseEntity<>(staffService.create(staff), HttpStatus.OK);
    }
    /**
     * Updates an existing StaffDTO with the given ID.
     *
     * @param staffDto The updated StaffDTO object
     * @param id       The ID of the StaffDTO to update
     * @return ResponseEntity containing the updated StaffDTO object and HTTP status OK
     * @throws Exception if an error occurs during update
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<StaffDTO> updateStaffDTO(@RequestBody @Valid StaffDTO staffDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(staffService.update(staffDto, id), HttpStatus.OK);
    }
    /**
     * Deletes a staff member with the given ID.
     *
     * @param id The ID of the staff member to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteStaffDTO(@PathVariable("id") Long id) {
        this.staffService.delete(id);
    }
    /**
     * Searches for staff members based on their name.
     *
     * @param name The name to search for
     * @return List of StaffDTO objects matching the search criteria
     */
    @GetMapping("/search/{name}")
    List<StaffDTO> searchStaffDTO(@PathVariable("name") String name) {
        return this.staffService.search(name);
    }
    /**
     * Filters staff members based on the store ID.
     *
     * @param storeId The ID of the store to filter by
     * @return List of StaffDTO objects filtered by the store ID
     */
    @GetMapping("/filter/{storeId}")
    List<StaffDTO> filterStaffDTO(@PathVariable("storeId") Long storeId) {
        return this.staffService.filter(storeId);
    }
}
