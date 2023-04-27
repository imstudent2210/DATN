package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.StaffDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.mapper.StaffMapper;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffMapper staffMapper;

    @GetMapping("/get-all")
    public ResponseEntity<List<StaffDTO>> getAllStaffDTO() {
        return new ResponseEntity<>(staffMapper.toStaffDTOs(staffRepository.findAll()), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Staff>> allProduct() {
        return new ResponseEntity<>(staffRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<StaffDTO> getStaffDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(staffMapper.toStaffDTO(staffService.find(id)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StaffDTO> createStaffDTO(@RequestBody @Valid Staff staff) throws Exception {
        return new ResponseEntity<>(staffService.create(staff), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StaffDTO> updateStaffDTO(@RequestBody @Valid StaffDTO staffDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(staffService.update(staffDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStaffDTO(@PathVariable("id") Long id) {
        this.staffService.delete(id);
    }

    @GetMapping("/search/{name}")
    List<StaffDTO> searchStaffDTO(@PathVariable("name") String name) {
        return this.staffService.search(name);
    }
    @GetMapping("/filter/{storeId}")
    List<StaffDTO> filterStaffDTO(@PathVariable("storeId") Long storeId) {
        return this.staffService.filter(storeId);
    }
}
