package com.graduate.touslestemp.domain.repository;


import com.graduate.touslestemp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
* @File:  UserRepository.java com.graduate.touslestemp.domain.repository
*
* @Author: TamNLT
* @Since: 20/6/2023 11:25 PM
* @Last update: 20/6/2023
*
* */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
