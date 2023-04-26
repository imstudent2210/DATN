package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.domain.repository.SalaryRepository;
import com.graduate.touslestemp.service.SalaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/staff-salary")
public class SalaryController {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private SalaryService salaryService;

    @GetMapping("/get")
    List<Salary> allSalary() {
        return this.salaryRepository.findAll();
    }

    @PostMapping("/create")
    public Salary createSalary(@RequestBody @Valid Salary salary) throws Exception {
        return this.salaryService.save(salary);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Salary> getCategoryById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(salaryService.find(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public Salary updateSalary(@RequestBody @Valid Salary salary, @PathVariable("id") Long id) throws Exception {
        return this.salaryService.update(salary , id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteSalary(@PathVariable("id") Long id) {
        this.salaryService.delete(id);
    }

}
