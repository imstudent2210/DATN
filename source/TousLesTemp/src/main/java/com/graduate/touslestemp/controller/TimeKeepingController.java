package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.StaffSalaryDTO;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import com.graduate.touslestemp.domain.mapper.TimeKeepingMapper;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.domain.repository.TimeKeepingRepository;
import com.graduate.touslestemp.service.TimeKeepingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @File: TimeKeepingController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:12 AM
 * @Update: 21/6/2023
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/staff-timekeeping")
public class TimeKeepingController {
    @Autowired
    private TimeKeepingRepository timeKeepingRepository;
    @Autowired
    private TimeKeepingService timeKeepingService;
    @Autowired
    private TimeKeepingMapper timeKeepingMapper;
    @Autowired
    private StaffRepository staffRepository;

    /**
     * Retrieves all timekeeping entities.
     *
     * @return ResponseEntity containing the list of TimeKeeping objects and HTTP status OK
     */
    @GetMapping("/gets")
    public ResponseEntity<List<TimeKeeping>> allTimeKeeping() {
        return new ResponseEntity<>(timeKeepingRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Retrieves all timekeeping entities as a list of TimeKeepingDTO objects.
     *
     * @return ResponseEntity containing the list of TimeKeepingDTO objects and HTTP status OK
     */
    @GetMapping("/get")
    public ResponseEntity<List<TimeKeepingDTO>> getAllTimeKeepingDTO() {
        return new ResponseEntity<>(timeKeepingMapper.toTimeKeepingDTOs(timeKeepingRepository.findAll()), HttpStatus.OK);
    }

    /**
     * Retrieves a specific timekeeping entity by its ID.
     *
     * @param id The ID of the timekeeping entity to retrieve
     * @return ResponseEntity containing the TimeKeepingDTO object and HTTP status OK
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<TimeKeepingDTO> getTimeKeepingDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(timeKeepingMapper.toTimeKeepingDTO(timeKeepingService.find(id)), HttpStatus.OK);
    }

    /**
     * Creates a new timekeeping entity.
     *
     * @param timeKeeping The TimeKeeping object representing the new timekeeping entity
     * @return ResponseEntity containing the created TimeKeepingDTO object and HTTP status OK
     * @throws Exception if an error occurs during creation
     */
    @PostMapping("/create")
    public ResponseEntity<TimeKeepingDTO> createTimeKeepingDTO(@RequestBody @Valid TimeKeeping timeKeeping) throws Exception {
        return new ResponseEntity<>(timeKeepingService.create(timeKeeping), HttpStatus.OK);
    }

    /**
     * Updates an existing timekeeping entity with the given ID.
     *
     * @param timeKeepingDTO The updated TimeKeepingDTO object
     * @param id             The ID of the timekeeping entity to update
     * @return ResponseEntity containing the updated TimeKeepingDTO object and HTTP status OK
     * @throws Exception if an error occurs during update
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<TimeKeepingDTO> updateTimeKeepingDTO(@RequestBody @Valid TimeKeepingDTO timeKeepingDTO, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(timeKeepingService.update(timeKeepingDTO, id), HttpStatus.OK);
    }

    /**
     * Deletes a timekeeping entity with the given ID.
     *
     * @param id The ID of the timekeeping entity to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteTimeKeepingDTO(@PathVariable("id") Long id) {
        this.timeKeepingService.delete(id);
    }

    /**
     * Retrieves the shift salary for a specific timekeeping entity by its ID.
     *
     * @param id The ID of the timekeeping entity
     * @return The shift salary of the timekeeping entity
     */
    @GetMapping("/get-shiftsalary/{id}")
    public int getshiftSalary(@PathVariable(name = "id") Long id) {
        return timeKeepingRepository.shiftSalary(id);
    }

    @GetMapping("/get-monthsalary")
    public int getmonthSalary(@RequestParam("id") Long id, @RequestParam("month") Long month) {
        return timeKeepingRepository.monthSalary(id, month);
    }

    /**
     * Retrieves the month salary for a specific timekeeping entity by staff ID and month.
     *
     * @param id    The ID of the staff
     * @param month The month for which to calculate the salary
     * @return The month salary of the staff
     */
    @GetMapping("/get-allstaff-timekeeping-permonth")
    public List<Staff> allStaffHaveTimeKeepingPerMonth(@RequestParam("month") long month) throws Exception {
        return this.timeKeepingService.allStaffHaveTimeKeepingPerMonth(month);
    }

    /**
     * Retrieves a list of staff entities that have timekeeping records for a specific month.
     *
     * @param month The month for which to retrieve the staff entities
     * @return List of Staff objects that have timekeeping records for the specified month
     * @throws Exception if an error occurs during retrieval
     */
    @GetMapping("/get-all-shiftsalary")
    public List<StaffSalaryDTO> calculateAllStaffSalary(@RequestParam("month") long month) throws Exception {
        return this.timeKeepingService.calculateAllStaffSalaryPerMonth(month);
    }

    /**
     * Retrieves all timekeeping records for a specific staff entity.
     *
     * @param id The ID of the staff entity
     * @return List of TimeKeepingDTO objects representing the timekeeping records for the staff
     */
    @GetMapping("/get-timekeeping-perstaff/{id}")
    public List<TimeKeepingDTO> getallTimeKeepingPerStaff(@PathVariable(name = "id") Long id) {
        return timeKeepingMapper.toTimeKeepingDTOs(timeKeepingRepository.allTimeKeepingPerStaff(id));
    }

    /**
     * Retrieves all timekeeping records for a specific month.
     *
     * @param id The ID of the month
     * @return List of TimeKeepingDTO objects representing the timekeeping records for the month
     */
    @GetMapping("/get-timekeeping-permonth/{id}")
    public List<TimeKeepingDTO> getallTimeKeepingPerMonth(@PathVariable(name = "id") Long id) {
        return timeKeepingMapper.toTimeKeepingDTOs(timeKeepingRepository.allTimeKeepingPerMonth(id));
    }

}
