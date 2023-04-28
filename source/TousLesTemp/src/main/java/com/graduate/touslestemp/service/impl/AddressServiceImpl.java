package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.repository.AddressRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.exception.RequestSuccess;
import com.graduate.touslestemp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        Address local = this.addressRepository.findAddressByName(address.getName());
        if (local != null) {
            System.out.println("This address has already");
            throw new RequestException("This address has already!");
        } else {
            local = this.addressRepository.save(address);
        }
        return local;
    }

    @Override
    public Address findAddress(String address) {
        Address local = this.addressRepository.findAddressByName(address);
        if (local == null) {
            System.out.println("Not found this address: " + address);
            throw new RequestException("Not found : " + address);
        } else
            return this.addressRepository.findAddressByName(address);

    }

    @Override
    public Address update(Address address, String addressname) {

        Address updateAddress = this.addressRepository.findAddressByName(addressname);
        String updateName = address.getName();
        Address local = this.addressRepository.findAddressByName(updateName);
        if (updateAddress == null) {
            System.out.println("Not found this address: " + addressname);
            throw new RequestException("Not found this address: " + addressname);
        } else {
            if (local == null)
                updateAddress.setName(address.getName());
            else
                throw new RequestException("This address has already: " + updateName);
        }
        return this.addressRepository.save(updateAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = new Address();
        address.setId(id);
        Optional<Address> local = this.addressRepository.findById(address.getId());
        if (!local.isPresent()) {
            System.out.println("Not found this address ");
            throw new RequestException("Not found this address id: " + id);
        } else {
            this.addressRepository.delete(address);
            throw new RequestSuccess("Delete address id " + id + " completed! ");
        }
    }

    @Override
    public Address update(Address address, Long id) throws Exception {
        Address local = this.addressRepository.findById(id)
                .orElseThrow(() -> new RequestException("Not found this address: " + id));

        if (isExisAddress(address.getName())) {
            System.out.println("This address has already");
            throw new RequestException("This address has already!");
        } else {
            local.setName(address.getName());
            return this.addressRepository.save(local);
        }
    }


    public boolean isExisAddress(String name) {
        Address checkAddress = addressRepository.findAddressByName(name);
        if (checkAddress != null) {
            return true;
        }
        return false;
    }
}
