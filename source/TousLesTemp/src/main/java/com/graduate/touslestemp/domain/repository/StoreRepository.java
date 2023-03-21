package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByName(String name);
    Page<Store> findAll(Pageable pageable);
    @Query("SELECT p FROM Store p WHERE p.name LIKE '%:name%'")
    Optional<Store> findStoreDTOByName(String name);
}
