package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByName(String address);

//    @Query(value = "")

}
