package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
* @File:  AddressRepository.java com.graduate.touslestemp.domain.repository
*
* @Author: TamNLT
* @Since: 20/6/2023 11:23 PM
* @Last update: 20/6/2023
*
* */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByName(String address);
}