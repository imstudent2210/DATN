package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @File: CategoryRepository.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:21 AM
 * @Update: 21/6/2023
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String category);
    @Query("select c from Category c where c.isActivated = true")
    List<Category> findCategoryActivated();

}
