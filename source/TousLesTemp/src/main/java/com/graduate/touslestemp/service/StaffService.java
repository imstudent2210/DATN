package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @File: StaffService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:28 AM
 * @Update: 21/6/2023
 */
public interface StaffService {
    /**
     * Retrieves a page of staff members based on the provided Pageable request.
     *
     * @param request The Pageable object containing pagination and sorting information.
     * @return A PageResponseDTO<?> object representing the page of staff members.
     */
    PageResponseDTO<?> getAllStaff(Pageable request);

    /**
     * Retrieves the staff member with the specified ID.
     *
     * @param id The ID of the staff member to be retrieved.
     * @return A Staff object representing the found staff member, or null if not found.
     */
    Staff find(Long id);

    /**
     * Creates a new staff member based on the provided Staff object.
     *
     * @param staff The Staff object containing the details of the staff member.
     * @return A StaffDTO object representing the created staff member.
     * @throws Exception if there is an error during the creation process.
     */
    StaffDTO create(Staff staff) throws Exception;

    /**
     * Deletes the staff member with the specified ID.
     *
     * @param id The ID of the staff member to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the staff member with the specified ID using the provided StaffDTO object.
     *
     * @param staffDto The StaffDTO object containing the updated staff member information.
     * @param id       The ID of the staff member to be updated.
     * @return A StaffDTO object representing the updated staff member.
     * @throws Exception if there is an error during the update process.
     */
    StaffDTO update(StaffDTO staffDto, Long id) throws Exception;

    /**
     * Searches for staff members based on the provided keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of StaffDTO objects representing the matched staff members.
     */
    List<StaffDTO> search(String keyword);

    /**
     * Filters staff members based on the specified ID.
     *
     * @param id The ID to filter the staff members by.
     * @return A list of StaffDTO objects representing the filtered staff members.
     */
    List<StaffDTO> filter(Long id);

}
