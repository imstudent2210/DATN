package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Size;
import com.graduate.touslestemp.domain.repository.SizeRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.utils.ExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;
    @GetMapping("/get")
    List <Size> getSize() {
        return this.sizeRepository.findAll();
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportStaffFile() throws Exception {
        List<Size> categories = this.sizeRepository.findAll();
        if(!CollectionUtils.isEmpty(categories)){
            String fileName = "Export File.xlsx";
            ByteArrayInputStream inputStream = ExportUtils.exportStaff(categories,fileName);
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename = " + URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel;charset=UTF-8"))
                    .body(inputStreamResource);
        }else{
            throw new RequestException("Không thể xuất file");
        }
    }
}
