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
/*
* @File:  SalaryController.java com.graduate.touslestemp.controller
*
* @Author: TamNLT
* @Since: 20/6/2023 11:10 PM
* @Last update: 20/6/2023
*
* */

@RestController
@CrossOrigin("*")
@RequestMapping("/staff-salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @GetMapping("/get-all")
    List<SalaryDTO> findAllSalaryDTO() {
        return this.salaryService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<SalaryDTO> createcreateSalaryDTO(@RequestBody @Valid Salary salary) throws Exception {
        return new ResponseEntity<>(salaryService.create(salary), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SalaryDTO> getSalaryDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(salaryService.find(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SalaryDTO> updateSalaryDTO(@RequestBody @Valid SalaryDTO salaryDTO, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(salaryService.update(salaryDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSalaryDTO(@PathVariable("id") Long id) {
        this.salaryService.delete(id);
    }


}
