package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
* @File:  CategoryRepository.java com.graduate.touslestemp.domain.repository
*
* @Author: TamNLT
* @Since: 20/6/2023 11:23 PM
* @Last update: 20/6/2023
*
* */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String category);
    @Query("select c from Category c where c.isActivated = true")
    List<Category> findCategoryActivated();

}
