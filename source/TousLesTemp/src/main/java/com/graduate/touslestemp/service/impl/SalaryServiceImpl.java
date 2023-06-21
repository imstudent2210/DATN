package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.domain.mapper.SalaryMapper;
import com.graduate.touslestemp.domain.repository.SalaryRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @File: SalaryServiceImpl.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:28 AM
 * @Update: 21/6/2023
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public List<SalaryDTO> findAll() {
        return salaryMapper.toSalaryDTOs(salaryRepository.findAll());
    }

    @Override
    public SalaryDTO create(Salary salary) throws Exception {
        if (isExisSalary(salary.getName())) {
            System.out.println("This salary has already");
            throw new RequestException("This salary has already!");
        } else {
            return (salaryMapper.toSalaryDTO(salaryRepository.save(salary)));
        }
    }

    @Override
    public void delete(Long id) {
        salaryRepository.delete(salaryRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found: " + id)));
    }

    @Override
    public SalaryDTO find(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);
        if (salary.isEmpty()) {
            throw new RequestException("Not found salary , id: " + id);
        }
        return salaryMapper.toSalaryDTO(salaryRepository.findById(id).get());
    }

    @Override
    public SalaryDTO update(SalaryDTO salary, Long id) throws Exception {
        Salary local = this.salaryRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found salary : " + id));
        salaryMapper.updateEntity(salary, local);
        return (salaryMapper.toSalaryDTO(salaryRepository.save(local)));
    }

    public boolean isExisSalary(String name) {
        Salary checkSalary = salaryRepository.findSalaryByName(name);
        if (checkSalary != null) {
            return true;
        }
        return false;
    }
}
