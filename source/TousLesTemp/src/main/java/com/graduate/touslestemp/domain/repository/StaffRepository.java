package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*
* @File:  StaffRepository.java com.graduate.touslestemp.domain.repository
*
* @Author: TamNLT
* @Since: 20/6/2023 11:25 PM
* @Last update: 20/6/2023
*
* */
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findStaffByName(String name);

    Page<Staff> findAll(Pageable pageable);

    @Query("select s from Staff s where s.name like :name")
    List<Staff> searchStaffByName(@Param("name") String name);

    @Query("select s from Staff s join Store a where s.store.id = a.id and a.id = :id")
    List<Staff> filterStaffByStoreId(@Param("id") Long id);

}
