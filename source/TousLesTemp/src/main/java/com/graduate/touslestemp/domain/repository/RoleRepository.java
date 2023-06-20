package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
* @File:  RoleRepository.java com.graduate.touslestemp.domain.repository
*
* @Author: TamNLT
* @Since: 20/6/2023 11:24 PM
* @Last update: 20/6/2023
*
* */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findByName(String name);
}
