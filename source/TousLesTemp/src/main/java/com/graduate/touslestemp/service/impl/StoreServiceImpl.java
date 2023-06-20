package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
* @File:  StoreServiceImpl.java com.graduate.touslestemp.service.impl
*
* @Author: TamNLT
* @Since: 20/6/2023 11:30 PM
* @Last update: 20/6/2023
*
* */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;
    final ModelMapper modelMapper = new ModelMapper();

    @Override
    public StoreDTO create(Store store) throws Exception {
        if (isExisStore(store.getName())) {
            System.out.println("This store has already");
            throw new RequestException("This store has already!");
        } else {
            return (storeMapper.toStoreDTO(storeRepository.save(store)));
        }
    }

    @Override
    public StoreDTO find(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isEmpty()) {
            throw new RequestException("Not found store, id: " + id);
        }
        return (storeMapper.toStoreDTO(storeRepository.findById(id).get()));
    }

    @Override
    public StoreDTO update(StoreDTO storeDto, Long id) throws Exception {
        Store local = this.storeRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this store id: " + id));
        storeMapper.updateEntity(storeDto, local);
        return (storeMapper.toStoreDTO(storeRepository.save(local)));
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(storeRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this store id: " + id)));
    }

    @Override
    public List<StoreDTO> search(String name) {
        List<Store> stores = this.storeRepository.searchStoreByName("%" + name + "%");
        if (stores.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StoreDTO> storeDTOS = this.storeMapper.toStoreDTOs(stores);
            return storeDTOS;
        }
    }

    @Override
    public List<StoreDTO> filter(Long id) {
        List<Store> stores = this.storeRepository.filterStoreByAddressId(id);
        if (stores.isEmpty()) {
            throw new RequestException("No data!");
        } else {
            List<StoreDTO> storeDTOS = this.storeMapper.toStoreDTOs(stores);
            return storeDTOS;
        }
    }

    @Override
    public List<Store> findAll() {
        return this.storeRepository.findAll();
    }

    @Override
    public PageResponseDTO<?> getAllStore(Pageable pageable) {
        return modelMapper.map(storeRepository.findAll(pageable), PageResponseDTO.class);
    }


    public boolean isExisStore(String name) {
        Store checkStore = storeRepository.findStoreByName(name);
        if (checkStore != null) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteStoreAndProduct(Long id) {
        storeRepository.delete(storeRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this store id: " + id)));
    }
}
