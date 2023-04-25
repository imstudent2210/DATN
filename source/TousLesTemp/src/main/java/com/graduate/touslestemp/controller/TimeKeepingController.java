package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Salary;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.mapper.TimeKeepingMapper;
import com.graduate.touslestemp.domain.repository.SalaryRepository;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.domain.repository.TimeKeepingRepository;
import com.graduate.touslestemp.service.SalaryService;
import com.graduate.touslestemp.service.TimeKeepingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void deleteTimeKeepingDTO(@PathVariable("id") Long id) throws Exception {
        this.timeKeepingService.delete(id);
    }

    @GetMapping("/get-shiftsalary/{id}")
    public int getshiftSalary(@PathVariable(name = "id") Long id) {
        return timeKeepingRepository.shiftSalary(id);
    }

    @GetMapping("/get-timekeeping-permonth/{id}")
    public List<TimeKeeping> getallTimeKeepingPerMonth(@PathVariable(name = "id") Long id) {
        return timeKeepingRepository.allTimeKeepingPerMonth(id);
    }

}