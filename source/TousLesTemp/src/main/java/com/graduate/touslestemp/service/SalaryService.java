package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.entity.Salary;

import java.util.List;
/*
* @File:  SalaryService.java com.graduate.touslestemp.service
*
* @Author: TamNLT
* @Since: 20/6/2023 11:30 PM
* @Last update: 20/6/2023
*
* */
public interface SalaryService {
    List<SalaryDTO> findAll();

    SalaryDTO create(Salary salary) throws Exception;

    void delete(Long id);

    SalaryDTO find(Long id);

    SalaryDTO update(SalaryDTO salary, Long id) throws Exception;
}
