package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StaffSalaryDTO;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @File: TimeKeepingService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:45 AM
 * @Update: 21/6/2023
 */
public interface TimeKeepingService {
    /**
     * Retrieves a page of timekeeping records based on the provided pageable request.
     *
     * @param request the pageable request containing pagination and sorting information
     * @return a PageResponseDTO containing the timekeeping records
     */
    PageResponseDTO<?> getAllTimeKeeping(Pageable request);

    /**
     * Retrieves a specific timekeeping record by its ID.
     *
     * @param id the ID of the timekeeping record to find
     * @return the found TimeKeeping object
     */
    TimeKeeping find(Long id);

    /**
     * Creates a new timekeeping record.
     *
     * @param timeKeeping the TimeKeeping object to create
     * @return the created TimeKeepingDTO object
     * @throws Exception if there's an error during the creation process
     */
    TimeKeepingDTO create(TimeKeeping timeKeeping) throws Exception;

    /**
     * Deletes a timekeeping record by its ID.
     *
     * @param id the ID of the timekeeping record to delete
     */
    void delete(Long id);

    /**
     * Updates an existing timekeeping record with new information.
     *
     * @param timeKeepingDTO the TimeKeepingDTO object containing the updated information
     * @param id             the ID of the timekeeping record to update
     * @return the updated TimeKeepingDTO object
     * @throws Exception if there's an error during the update process
     */
    TimeKeepingDTO update(TimeKeepingDTO timeKeepingDTO, Long id) throws Exception;

    /**
     * Searches for timekeeping records based on a provided keyword.
     *
     * @param keyword the keyword to search for in the timekeeping records
     * @return a list of TimeKeepingDTO objects matching the search criteria
     */
    List<TimeKeepingDTO> search(String keyword);

    /**
     * Filters timekeeping records based on a provided ID.
     *
     * @param id the ID to filter the timekeeping records by
     * @return a list of TimeKeepingDTO objects matching the filter criteria
     */
    List<TimeKeepingDTO> filter(Long id);

    /**
     * Retrieves a list of all staff members who have timekeeping records for a specific month.
     *
     * @param month the month for which to retrieve the timekeeping records
     * @return a list of Staff objects who have timekeeping records in the specified month
     * @throws Exception if there's an error during the retrieval process
     */
    List<Staff> allStaffHaveTimeKeepingPerMonth(Long month) throws Exception;

    /**
     * Calculates the salary for all staff members for a specific month.
     *
     * @param month the month for which to calculate the staff salaries
     * @return a list of StaffSalaryDTO objects containing the calculated salaries
     * @throws Exception if there's an error during the calculation process
     */
    List<StaffSalaryDTO> calculateAllStaffSalaryPerMonth(Long month) throws Exception;

}
