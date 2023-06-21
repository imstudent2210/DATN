package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.StaffPerGroupDTO;
import com.graduate.touslestemp.domain.dto.StaffSalaryDTO;
import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.StaffGroup;

import java.util.List;

/**
 * @File: StaffGroupService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:33 AM
 * @Update: 21/6/2023
 */
public interface StaffGroupService {
    /**
     * Retrieves a list of all staff groups.
     *
     * @return A list of StaffGroup objects representing the staff groups.
     */
    List<StaffGroup> findAll();

    /**
     * Saves a new staff group based on the provided StaffGroup object.
     *
     * @param category The StaffGroup object containing the details of the staff group.
     * @return A StaffGroup object representing the saved staff group.
     * @throws Exception if there is an error during the saving process.
     */
    StaffGroup save(StaffGroup category) throws Exception;

    /**
     * Deletes the staff group with the specified ID.
     *
     * @param id The ID of the staff group to be deleted.
     */
    void deleteStaffGroup(Long id);

    /**
     * Retrieves the staff group with the specified ID.
     *
     * @param id The ID of the staff group to be retrieved.
     * @return A StaffGroup object representing the found staff group, or null if not found.
     */
    StaffGroup findStaffGroup(Long id);

    /**
     * Updates the staff group with the specified ID using the provided StaffGroup object.
     *
     * @param category The StaffGroup object containing the updated staff group information.
     * @param id       The ID of the staff group to be updated.
     * @return A StaffGroup object representing the updated staff group.
     * @throws Exception if there is an error during the update process.
     */
    StaffGroup update(StaffGroup category, Long id) throws Exception;
}
