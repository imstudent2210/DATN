package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.dto.StoreDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {
    List<Store> findAll();
    Store save(Store store) throws Exception;
    Store findStore(String name);
    Store update(Store store, String name) throws Exception;
//    void deleteStore (Long id);

    /*====================== DTO==================*/
    PageResponseDTO<?> getAllStore(Pageable request);
    StoreDto find(Long id);
    StoreDto create(Store store) throws Exception;
    void delete (Long id);
    StoreDto update(StoreDto storeDto, Long id) throws Exception;
    List<StoreDto> search(String keyword);
    List<StoreDto> filter(Long id);
}
