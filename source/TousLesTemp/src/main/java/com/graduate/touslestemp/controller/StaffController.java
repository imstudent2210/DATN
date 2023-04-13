package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.StaffDto;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.mapper.StaffMapper;
import com.graduate.touslestemp.domain.repository.StaffRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.StaffService;
import com.graduate.touslestemp.utils.ExportUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<List<StaffDto>> getAllStaffDTO() {
        return new ResponseEntity<>(staffMapper.toStaffDTOs(staffRepository.findAll()), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Staff>> allProduct() {
        return new ResponseEntity<>(staffRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Staff> getStaffDTO(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(staffService.find(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StaffDto> createStaffDTO(@RequestBody @Valid Staff staff) throws Exception {
        return new ResponseEntity<>(staffService.create(staff), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StaffDto> updateStaffDTO(@RequestBody @Valid StaffDto staffDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(staffService.update(staffDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStaffDTO(@PathVariable("id") Long id) throws Exception {
        this.staffService.delete(id);
    }

    @GetMapping("/search/{store}")
    List<StaffDto> searchStaffDTO(@PathVariable("name") String name) throws Exception {
        return this.staffService.search(name);
    }
    @GetMapping("/filter/{addressId}")
    List<StaffDto> filterStaffDTO(@PathVariable("storeid") Long storeid) throws Exception {
        return this.staffService.filter(storeid);
    }

    @PostMapping(value = {"/create2"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE, })
    public ResponseEntity<Staff> createStaff(@RequestPart("staff") @Valid Staff staff, @RequestPart("file") MultipartFile[] file) throws Exception {
        return new ResponseEntity<>(staffService.create2(staff, file), HttpStatus.OK);
    }
    @PutMapping(value = "/update2/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE, })
    public ResponseEntity<Staff> updateStaff(@RequestPart("staff") @Valid Staff staff, @PathVariable(name = "id") Long id,  @RequestPart("file") MultipartFile[] file) throws Exception {
        return new ResponseEntity<>(staffService.update2(staff, id, file), HttpStatus.OK);
    }

    @GetMapping("/get2/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(staffService.find(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete2/{id}")
    public void deleteStaff(@PathVariable("id") Long id) throws Exception{
        this.staffService.delete(id);
    }

//    @GetMapping("/export")
//    public ResponseEntity<InputStreamResource> exportStaffFile() throws Exception {
//        List<Staff> staffList = this.staffRepository.findAll();
//        if(!CollectionUtils.isEmpty(staffList)){
//            String fileName = "Staff Export File "+".xlsx";
//            ByteArrayInputStream inputStream = ExportUtils.exportStaff(staffList,fileName);
//            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            "attachment; filename = " + URLEncoder.encode(fileName, StandardCharsets.UTF_8))
//                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel;charset=UTF-8"))
//                    .body(inputStreamResource);
//        }else{
//        throw new RequestException("Không thể xuất file");
//        }
//    }
}
