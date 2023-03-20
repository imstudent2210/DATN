package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface StoreService {
    List<Store> findAll();
    Store save(Store store) throws Exception;
    Store findStore(String name);
    Store update(Store store, String name) throws Exception;
    void deleteStore (Long id);
    PageResponseDTO<?> getAllStore(Pageable request);


}
