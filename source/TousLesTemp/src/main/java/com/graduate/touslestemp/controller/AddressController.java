package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.repository.AddressRepository;
import com.graduate.touslestemp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/get")
    List<Address> allAddress() {
        return this.addressRepository.findAll();
    }

    @PostMapping("/create")
    public Address createAddress(@RequestBody @Valid Address address) throws Exception {
        address.setName(address.getName());
        return this.addressService.save(address);
    }

    @GetMapping("/get/{address}")
    Address getAddressByName(@PathVariable("address") String address) throws Exception {
        return this.addressService.findAddress(address);

    }

    @PutMapping("/update2/{addressname}")
    public Address updateAddres(@RequestBody @Valid Address address, @PathVariable("addressname") String addressname) throws Exception {
        return this.addressService.update(address, addressname);
    }
    @PutMapping("/update/{id}")
    public Address updateAddress(@RequestBody @Valid Address address, @PathVariable("id") Long id) throws Exception {
        return this.addressService.update(address, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) throws Exception{
       this.addressService.deleteAddress(id);
    }

}
