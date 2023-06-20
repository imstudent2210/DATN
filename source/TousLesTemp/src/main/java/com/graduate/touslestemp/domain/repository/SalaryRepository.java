package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
/*
* @File:  SalaryRepository.java com.graduate.touslestemp.domain.repository
*
* @Author: TamNLT
* @Since: 20/6/2023 11:24 PM
* @Last update: 20/6/2023
*
* */
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Salary findSalaryByName(String name);
}
