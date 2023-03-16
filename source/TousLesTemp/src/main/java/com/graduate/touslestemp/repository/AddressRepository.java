package com.graduate.touslestemp.repository;

import com.graduate.touslestemp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByName(String address);
}