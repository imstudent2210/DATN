package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.model.Address;
import com.graduate.touslestemp.repository.AddressRepository;
import com.graduate.touslestemp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Address save(Address address) {
        Address local = this.addressRepository.findAddressByName(address.getName());
        if(local !=null){
            System.out.println("This address has already");
            throw new RequestException("This address has already!");
        }else{


            local = this.addressRepository.save(address);

        }
        return local;
    }


    @Override
    public Address update(Address address) {
        return null;
    }
    @Override
    public Address findAddress(String address) {
        Address local = this.addressRepository.findAddressByName(address);
        if (local == null) {
            System.out.println("Not found this account: " + address);
            throw new RequestException("Not found : " + address);
        } else
            return this.addressRepository.findAddressByName(address);

    }
}
