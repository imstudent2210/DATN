package com.graduate.touslestemp.domain.repository;


import com.graduate.touslestemp.config.authenticate.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
