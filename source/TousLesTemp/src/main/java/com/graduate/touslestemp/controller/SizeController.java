package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Size;
import com.graduate.touslestemp.domain.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
