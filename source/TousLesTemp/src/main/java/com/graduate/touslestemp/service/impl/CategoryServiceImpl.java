package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.repository.CategoryRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.exception.RequestSuccess;
import com.graduate.touslestemp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) throws Exception {
        if (isExisCategory(category.getName())) {
            System.out.println("This category has already");
            throw new RequestException("This category has already!");
        } else return this.categoryRepository.save(category);
    }

    @Override
    public Category findCategory(String name) {
        if (!isExisCategory(name)) {
            System.out.println("Not found this category");
            throw new RequestException("Not found this category: "+name);
        } else return this.categoryRepository.findCategoryByName(name);
    }

    @Override
    public Category update(Category category, String name) throws Exception {
        Category updateCategory = this.categoryRepository.findCategoryByName(name);
        String updateName = category.getName();
        Category local = this.categoryRepository.findCategoryByName(updateName);
        if (updateCategory == null) {
            System.out.println("Not found this category: " + name);
            throw new RequestException("Not found this category: " + name);
        } else {
            if (local == null) updateCategory.setName(category.getName());
            else throw new RequestException("This category has already: " + updateName);
        }
        return this.categoryRepository.save(updateCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = new Category();
        category.setId(id);
        Optional<Category> local = this.categoryRepository.findById(category.getId());
        if (!local.isPresent()) {
            System.out.println("Not found this category ");
            throw new RequestException("Not found category id: " + id);
        } else {
            category.setActivated(false);
            category.setName(local.get().getName());
            categoryRepository.save(category);
            throw new RequestSuccess("Delete category id " + id + " completed! ");
        }
    }

    public boolean isExisCategory(String name) {
        Category checkCategory = categoryRepository.findCategoryByName(name);
        if (checkCategory != null) {
            return true;
        }
        return false;
    }
}
