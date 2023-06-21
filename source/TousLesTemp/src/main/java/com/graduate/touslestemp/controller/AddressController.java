package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.repository.AddressRepository;
import com.graduate.touslestemp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @File: AddressController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:11 AM
 * @Update: 21/6/2023
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    /**
     * Retrieves all addresses.
     *
     * @return The list of all addresses.
     */
    @GetMapping("/get")
    List<Address> allAddress() {
        return this.addressRepository.findAll();
    }

    /**
     * Creates a new address.
     *
     * @param address The address object to be created.
     * @return The created address.
     * @throws Exception if an error occurs during the address creation process.
     */
    @PostMapping("/create")
    public Address createAddress(@RequestBody @Valid Address address) throws Exception {
        address.setName(address.getName());
        return this.addressService.save(address);
    }

    /**
     * Retrieves an address by its name.
     *
     * @param address The name of the address to be retrieved.
     * @return The address matching the provided name.
     */
    @GetMapping("/get/{address}")
    Address getAddressByName(@PathVariable("address") String address) {
        return this.addressService.findAddress(address);

    }

    /**
     * Updates an address by its name.
     *
     * @param address      The updated address object.
     * @param addressName  The name of the address to be updated.
     * @return The updated address.
     * @throws Exception if an error occurs during the address update process.
     */
    @PutMapping("/update2/{addressName}")
    public Address updateAddress(@RequestBody @Valid Address address, @PathVariable("addressName") String addressName) throws Exception {
        return this.addressService.update(address, addressName);
    }

    /**
     * Updates an address by its ID.
     *
     * @param address The updated address object.
     * @param id      The ID of the address to be updated.
     * @return The updated address.
     * @throws Exception if an error occurs during the address update process.
     */
    @PutMapping("/update/{id}")
    public Address updateAddress(@RequestBody @Valid Address address, @PathVariable("id") Long id) throws Exception {
        return this.addressService.update(address, id);
    }

    /**
     * Deletes an address by its ID.
     *
     * @param id The ID of the address to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
       this.addressService.deleteAddress(id);
    }

}
