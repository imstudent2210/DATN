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

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/gets")
    public ResponseEntity<List<TimeKeeping>> allTimeKeeping() {
        return new ResponseEntity<>(timeKeepingRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TimeKeepingDTO>> getAllTimeKeepingDTO() {
        return new ResponseEntity<>(timeKeepingMapper.toTimeKeepingDTOs(timeKeepingRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TimeKeeping> getTimeKeepingDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(timeKeepingService.find(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TimeKeepingDTO> createTimeKeepingDTO(@RequestBody @Valid TimeKeeping timeKeeping) throws Exception {
        return new ResponseEntity<>(timeKeepingService.create(timeKeeping), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TimeKeepingDTO> updateTimeKeepingDTO(@RequestBody @Valid TimeKeepingDTO timeKeepingDTO, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(timeKeepingService.update(timeKeepingDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTimeKeepingDTO(@PathVariable("id") Long id) {
        this.timeKeepingService.delete(id);
    }

    @GetMapping("/get-shiftsalary/{id}")
    public int getshiftSalary(@PathVariable(name = "id") Long id) {
        return timeKeepingRepository.shiftSalary(id);
    }
    @GetMapping("/get-monthsalary")
    public int getmonthSalary(@RequestParam("id") Long id, @RequestParam("month") Long month) {
        return timeKeepingRepository.monthSalary(id,month);
    }

    @GetMapping("/get-timekeeping-permonth/{id}")
    public List<TimeKeeping> getallTimeKeepingPerMonth(@PathVariable(name = "id") Long id) {
        return timeKeepingRepository.allTimeKeepingPerMonth(id);
    }

    @GetMapping("/get-timekeeping-perstaff/{id}")
    public List<TimeKeeping> getallTimeKeepingPerStaff(@PathVariable(name = "id") Long id) {
        return timeKeepingRepository.allTimeKeepingPerStaff(id);
    }

    @GetMapping("/get-all-shiftsalary")
    public List<StaffSalaryDTO> calculateAllStaffSalary(@RequestParam("month") long month) throws Exception {
        return this.timeKeepingService.calculateAllStaffSalaryPerMonth(month);
    }
    @GetMapping("/get-allstaff-timekeeping-permonth")
    public List<Staff> allStaffHaveTimeKeepingPerMonth(@RequestParam("month") long month) throws Exception {
        return this.timeKeepingService.allStaffHaveTimeKeepingPerMonth(month);
    }
}
