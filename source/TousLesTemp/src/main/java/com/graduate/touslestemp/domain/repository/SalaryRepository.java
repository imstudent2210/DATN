package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
    Salary findSalaryByName(String name);
}
