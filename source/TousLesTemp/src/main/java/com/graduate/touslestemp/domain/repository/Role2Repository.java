package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role2Repository extends JpaRepository<Role, Long> {
        Role findByName(String name);
}
