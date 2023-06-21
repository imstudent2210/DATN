package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.service.SalaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @File:
 * @Author: TamNLT
 * @Since: 21/6/2023 9:10 AM
 * @Update: 21/6/2023
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/staff-salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    /**
     * Retrieves all SalaryDTO objects.
     *
     * @return List of SalaryDTO objects
     */
    @GetMapping("/get-all")
    List<SalaryDTO> findAllSalaryDTO() {
        return this.salaryService.findAll();
    }

    /**
     * Creates a new SalaryDTO.
     *
     * @param salary The Salary object to be created
     * @return ResponseEntity containing the created SalaryDTO and HTTP status OK
     * @throws Exception if an error occurs during creation
     */
    @PostMapping("/create")
    public ResponseEntity<SalaryDTO> createcreateSalaryDTO(@RequestBody @Valid Salary salary) throws Exception {
        return new ResponseEntity<>(salaryService.create(salary), HttpStatus.OK);
    }

    /**
     * Retrieves a specific SalaryDTO by its ID.
     *
     * @param id The ID of the SalaryDTO to retrieve
     * @return ResponseEntity containing the retrieved SalaryDTO and HTTP status OK
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<SalaryDTO> getSalaryDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(salaryService.find(id), HttpStatus.OK);
    }

    /**
     * Updates an existing SalaryDTO.
     *
     * @param salaryDTO The updated SalaryDTO object
     * @param id        The ID of the SalaryDTO to update
     * @return ResponseEntity containing the updated SalaryDTO and HTTP status OK
     * @throws Exception if an error occurs during update
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<SalaryDTO> updateSalaryDTO(@RequestBody @Valid SalaryDTO salaryDTO, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(salaryService.update(salaryDTO, id), HttpStatus.OK);
    }

    /**
     * Deletes a SalaryDTO by its ID.
     *
     * @param id The ID of the SalaryDTO to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteSalaryDTO(@PathVariable("id") Long id) {
        this.salaryService.delete(id);
    }


}
