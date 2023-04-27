package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.entity.Salary;

import java.util.List;

public interface SalaryService {
    List<SalaryDTO> findAll();
    SalaryDTO create(Salary salary) throws Exception;
    void delete(Long id);
    SalaryDTO find(Long id);
    SalaryDTO update(SalaryDTO salary, Long id) throws Exception;
}
