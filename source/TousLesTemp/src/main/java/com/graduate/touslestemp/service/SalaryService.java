package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Salary;

import java.util.List;

public interface SalaryService {
    List<Salary> findAll();
    Salary save(Salary salary) throws Exception;
    void delete(Long id);
    Salary find(Long id);
    Salary update(Salary salary, Long id) throws Exception;
}
