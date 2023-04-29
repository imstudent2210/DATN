package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Category;

import java.util.List;

public interface AddressService {
    List<Address> findAll();

    Address save(Address address) throws Exception;

    Address findAddress(String name);

    Address update(Address address, String name) throws Exception;

    void deleteAddress(Long id);

    Address update(Address address, Long id) throws Exception;
}
