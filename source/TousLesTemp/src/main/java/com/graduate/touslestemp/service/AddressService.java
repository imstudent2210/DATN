package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address save(Address address) throws Exception;
    Address findAddress(String name);

    Address update(Address address, String name) throws Exception;
//    void delete(String name) throws Exception;
    void deleteAddress (Long id);




}
