package com.graduate.touslestemp.domain.repository;


import com.graduate.touslestemp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @File: UserRepository.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:21 AM
 * @Update: 21/6/2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
