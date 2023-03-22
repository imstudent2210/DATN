package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByName(String name);
    Page<Store> findAll(Pageable pageable);
    @Query("select s from Store s where s.name like :name" )
    List<Store> searchStoreByName(@Param("name") String name);

    @Query("select s from Store s join Address a where s.address.id = a.id and a.id = :id" )
    List<Store> filterStoreByAddressId(@Param("id") Long id);

}
