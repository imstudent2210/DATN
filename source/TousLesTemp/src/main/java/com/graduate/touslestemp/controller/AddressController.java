package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.model.Account;
import com.graduate.touslestemp.model.Address;
import com.graduate.touslestemp.repository.AddressRepository;
import com.graduate.touslestemp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/get")
    List<Address> allAddress(){
        return this.addressRepository.findAll();
    }
    @PostMapping("/create")
    public Address createAddress(@RequestBody @Valid Address address) throws Exception{
        address.setName(address.getName());
        return this.addressService.save(address);
    }
    @GetMapping("/get/{address}")
    Address getUserByName(@PathVariable("address") String address) throws Exception{
        return this.addressService.findAddress(address);

    }
}
