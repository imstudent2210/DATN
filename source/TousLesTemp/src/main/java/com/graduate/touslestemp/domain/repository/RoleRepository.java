package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @File: RoleRepository.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:22 AM
 * @Update: 21/6/2023
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findByName(String name);
}
