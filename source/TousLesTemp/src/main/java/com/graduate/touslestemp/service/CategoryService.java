package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
* @File:  CategoryService.java com.graduate.touslestemp.service
*
* @Author: TamNLT
* @Since: 20/6/2023 11:30 PM
* @Last update: 20/6/2023
*
* */
public interface CategoryService {
    List<Category> findAll();

    Category save(Category category) throws Exception;

    Category findCategory(String name);

    Category updateByName(Category category, String name) throws Exception;

    void deleteCategory(Long id);

    Category findCategory(Long id);

    Category update(Category category, Long id) throws Exception;


}
