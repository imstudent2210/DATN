package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @File: SalaryRepository.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:22 AM
 * @Update: 21/6/2023
 */
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Salary findSalaryByName(String name);
}
