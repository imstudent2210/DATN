package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Category;

import java.util.List;
/*
* @File:  AddressService.java com.graduate.touslestemp.service
*
* @Author: TamNLT
* @Since: 20/6/2023 11:30 PM
* @Last update: 20/6/2023
*
* */
public interface AddressService {
    List<Address> findAll();

    Address save(Address address) throws Exception;

    Address findAddress(String name);

    Address update(Address address, String name) throws Exception;

    void deleteAddress(Long id);

    Address update(Address address, Long id) throws Exception;
}
