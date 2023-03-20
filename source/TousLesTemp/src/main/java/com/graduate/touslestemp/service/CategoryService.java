package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category) throws Exception;
    Category findCategory(String name);

    Category update(Category category, String name) throws Exception;
    //    void delete(String name) throws Exception;
    void deleteCategory (Long id);

}
