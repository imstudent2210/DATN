package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {
    List<Store> findAll();
    PageResponseDTO<?> getAllStore(Pageable request);
    StoreDTO find(Long id);
    StoreDTO create(Store store) throws Exception;
    void delete (Long id);
    StoreDTO update(StoreDTO storeDto, Long id) throws Exception;
    List<StoreDTO> search(String keyword);
    List<StoreDTO> filter(Long id);
    void deleteStoreAndProduct(Long id);

}
