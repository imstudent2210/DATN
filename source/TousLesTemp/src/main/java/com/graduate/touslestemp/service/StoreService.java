package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;

import java.util.List;

public interface StoreService {
    List<Store> findAll();
    Store save(Store store) throws Exception;
    Store findStore(String name);
    Store update(Store store, String name) throws Exception;
//    Store update(StoreDTO storeDTO, String name) throws Exception;
    void deleteStore (Long id);
}
