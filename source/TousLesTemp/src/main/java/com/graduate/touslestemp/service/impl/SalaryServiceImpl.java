package com.graduate.touslestemp.service.impl;
import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.domain.repository.SalaryRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import com.graduate.touslestemp.exception.ForeignKeyConstraintException;
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> findAll() {
        return this.salaryRepository.findAll();
    }

    @Override
    public Salary save(Salary salary) throws Exception {
        if (isExisSalary(salary.getName())) {
            System.out.println("This salary has already");
            throw new RequestException("This salary has already!");
        } else return this.salaryRepository.save(salary);
    }

    @Override
    public void delete(Long id) {
        try {
            // Attempt to delete or update the parent record
            salaryRepository.delete(salaryRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this salary: " + id)));
        } catch (DataIntegrityViolationException e) {
            // Check if the exception is caused by a foreign key constraint violation
            if (e.getCause() != null) {
                // Throw a custom exception with a meaningful error message
                throw new ForeignKeyConstraintException("Cannot delete or update a parent row: a foreign key constraint fails");
            } else {
                // If it's not a foreign key constraint violation, re-throw the original exception
                throw e;
            }
        }
    }

    @Override
    public Salary find(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);
        if (salary.isEmpty()) {
            throw new RequestException("Not found salary, id: " + id);
        }
        return salaryRepository.findById(id).get();
    }

    @Override
    public Salary update(Salary salary, Long id) throws Exception {
        Optional<Salary> local = salaryRepository.findById(id);
        if (local.isEmpty()) {
            throw new RequestException("Not found salary, id: " + id);
        } else {
            Salary updateSalary = this.salaryRepository.findSalaryByName(local.get().getName());
            String updateName = salary.getName();
            Salary local1 = this.salaryRepository.findSalaryByName(updateName);

            if (updateSalary == null) {
                System.out.println("Not found this salary: " + id);
                throw new RequestException("Not found this salary: " + id);
            } else {
                if (local1 == null | (local1 != null && (local1.getBasicSalary() != salary.getBasicSalary()))) {
                    updateSalary.setName(salary.getName());
                    updateSalary.setBasicSalary(salary.getBasicSalary());
                } else throw new RequestException("This salary has already: " + updateName);
            }
            return this.salaryRepository.save(updateSalary);
        }
    }
    public boolean isExisSalary(String name) {
        Salary checkSalary = salaryRepository.findSalaryByName(name);
        if (checkSalary != null) {
            return true;
        }
        return false;
    }
}
