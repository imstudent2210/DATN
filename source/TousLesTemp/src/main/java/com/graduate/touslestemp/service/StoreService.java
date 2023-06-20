package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Pageable;

import java.util.List;
/*
* @File:  StoreService.java com.graduate.touslestemp.service
*
* @Author: TamNLT
* @Since: 20/6/2023 11:30 PM
* @Last update: 20/6/2023
*
* */
public interface StoreService {
    List<Store> findAll();

    PageResponseDTO<?> getAllStore(Pageable request);

    StoreDTO find(Long id);

    StoreDTO create(Store store) throws Exception;

    void delete(Long id);

    StoreDTO update(StoreDTO storeDto, Long id) throws Exception;

    List<StoreDTO> search(String keyword);

    List<StoreDTO> filter(Long id);

    void deleteStoreAndProduct(Long id);

}
