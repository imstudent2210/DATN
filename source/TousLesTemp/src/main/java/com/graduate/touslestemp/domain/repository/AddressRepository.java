package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @File: AddressRepository.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:21 AM
 * @Update: 21/6/2023
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByName(String address);
}