package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.repository.AddressRepository;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Store> findAll() {
        return this.storeRepository.findAll();
    }


    //save store using model mapper
    @Override
    public Store save(Store store) throws Exception {
        Store local = this.storeRepository.findStoreByName(store.getName());
        if (local != null) {
            System.out.println("This store has already");
            throw new RequestException("This store has already!");
        } else {
            Address address = store.getAddress();
            if(this.addressRepository.findAddressById(address.getId()) == null)
                throw new RequestException("This address not exist!");
            else
            {

                address.setName(address.getName());
                store.setAddress(address);

                return modelMapper.map(
                        storeRepository.save(modelMapper.map(store, Store.class)),
                        Store.class
                );
            }
        }
    }


    @Override
    public Store findStore(String store) {
        Store local = this.storeRepository.findStoreByName(store);
        if (local == null) {
            System.out.println("Not found this store: " + store);
            throw new RequestException("Not found this store: " + store);
        } else
            return this.storeRepository.findStoreByName(store);
    }

    @Override
    public Store update(Store store, String name) throws Exception {
        Store updateStore = this.storeRepository.findStoreByName(name);
        String updateName = store.getName();

        if (updateStore == null) {
            System.out.println("Not found this store: " + name);
            throw new RequestException("Not found this store: " + name);
        } else {

            Address address = store.getAddress();
            address.setName(address.getName());
            store.setAddress(address);

            updateStore.setName(store.getName());
            updateStore.setPhone(store.getPhone());
            updateStore.setEmail(store.getEmail());
            updateStore.setAddress(store.getAddress());
            updateStore.setProducts(store.getProducts());

        }
        return this.storeRepository.save(updateStore);
    }


    // method delete 2
    @Override
    public void deleteStore(Long id) {
        storeRepository.delete(storeRepository.findById(id).orElseThrow(() -> new RequestException("Can't found this store id: " + id)));
    }


}