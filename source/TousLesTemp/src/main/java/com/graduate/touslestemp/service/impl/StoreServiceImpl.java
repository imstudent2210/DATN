package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.StoreDto;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.AddressRepository;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreService storeService;

    /*====================DTO v2==========================*/
    @Override
    public StoreDto create(Store store) throws Exception {
        if (isExisStore(store.getName())) {
            System.out.println("This store has already");
            throw new RequestException("This store has already!");
        } else {
            return (storeMapper.toStoreDTO(storeRepository.save(store)));
        }
    }

    @Override
    public StoreDto find(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isEmpty()) {
            throw new RequestException("Not found store, id: " + id);
        }
        return (storeMapper.toStoreDTO(storeRepository.findById(id).get()));
    }

    @Override
    public StoreDto update(StoreDto storeDto, Long id) throws Exception {
        Store local = this.storeRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this store id: " + id));
        storeMapper.updateEntity(storeDto, local);
        return (storeMapper.toStoreDTO(storeRepository.save(local)));
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(storeRepository.findById(id).orElseThrow(() -> new RequestException("Can't found this store id: " + id)));
    }

    /*===================end DTO==========================*/
    @Override
    public List<Store> findAll() {
        return this.storeRepository.findAll();
    }


    //save store using model mapper
    @Override
    public Store save(Store store) throws Exception {
        if (isExisStore(store.getName())) {
            System.out.println("This store has already");
            throw new RequestException("This store has already!");
        } else {
            Address address = store.getAddress();
            if (this.addressRepository.findAddressById(address.getId()) == null)
                throw new RequestException("This address not exist!");
            else {
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
    public Store update(Store store, String name) throws Exception {
        Store updateStore = this.storeRepository.findStoreByName(name);

        if (updateStore == null) {
            System.out.println("Not found this store: " + name);
            throw new RequestException("Not found this store: " + name);
        } else {
            if (isExisStore(store.getName())) {
                System.out.println("This store has already");
                throw new RequestException("This store has already!");
            } else {
                Address address = store.getAddress();
                if (this.addressRepository.findAddressById(address.getId()) == null)
                    throw new RequestException("This address not exist!");

                address.setName(address.getName());
                store.setAddress(address);

                updateStore.setName(store.getName());
                updateStore.setPhone(store.getPhone());
                updateStore.setEmail(store.getEmail());
                updateStore.setAddress(store.getAddress());
//                updateStore.setProducts(store.getProducts());

            }
        }
        return this.storeRepository.save(updateStore);
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


    public boolean isExisStore(String name) {
        Store checkStore = storeRepository.findStoreByName(name);
        if (checkStore != null) {
            return true;
        }
        return false;
    }

    @Override
    public PageResponseDTO<?> getAllStore(Pageable pageable) {
        return modelMapper.map(storeRepository.findAll(pageable), PageResponseDTO.class);
    }

}
