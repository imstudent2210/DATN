package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByName(String name);
    Page<Store> findAll(Pageable pageable);
    Optional<Store> findStoreDTOByName(String name);
}
