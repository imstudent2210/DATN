package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.SalaryDTO;
import com.graduate.touslestemp.domain.entity.Salary;

import java.util.List;

/**
 * @File: SalaryService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:30 AM
 * @Update: 21/6/2023
 */
public interface SalaryService {
    /**
     * Retrieves a list of all salaries.
     *
     * @return A list of SalaryDTO objects representing the salaries.
     */
    List<SalaryDTO> findAll();

    /**
     * Creates a new salary entry based on the provided Salary object.
     *
     * @param salary The Salary object containing the details of the salary.
     * @return A SalaryDTO object representing the created salary entry.
     * @throws Exception if there is an error during the creation process.
     */
    SalaryDTO create(Salary salary) throws Exception;

    /**
     * Deletes the salary entry with the specified ID.
     *
     * @param id The ID of the salary entry to be deleted.
     */
    void delete(Long id);

    /**
     * Retrieves the salary entry with the specified ID.
     *
     * @param id The ID of the salary entry to be retrieved.
     * @return A SalaryDTO object representing the found salary entry, or null if not found.
     */
    SalaryDTO find(Long id);

    /**
     * Updates the salary entry with the specified ID using the provided SalaryDTO object.
     *
     * @param salary The SalaryDTO object containing the updated salary information.
     * @param id     The ID of the salary entry to be updated.
     * @return A SalaryDTO object representing the updated salary entry.
     * @throws Exception if there is an error during the update process.
     */
    SalaryDTO update(SalaryDTO salary, Long id) throws Exception;
}
