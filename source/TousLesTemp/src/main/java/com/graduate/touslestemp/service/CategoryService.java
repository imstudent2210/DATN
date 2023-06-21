package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @File: CategoryService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:29 AM
 * @Update: 21/6/2023
 */
public interface CategoryService {
    /**
     * Retrieves all category.
     *
     * @return A list of all category.
     */
    List<Category> findAll();

    /**
     * Saves a category.
     *
     * @param category The category to be saved.
     * @return The saved category.
     * @throws Exception If an error occurs during the save process.
     */
    Category save(Category category) throws Exception;

    /**
     * Finds a category by its name.
     *
     * @param name The name of the category to find.
     * @return The category matching the provided name.
     */
    Category findCategory(String name);

    /**
     * Updates a category identified by its name.
     *
     * @param category The updated category.
     * @param name     The name of the category to update.
     * @return The updated category.
     * @throws Exception If an error occurs during the update process.
     */
    Category updateByName(Category category, String name) throws Exception;

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     */
    void deleteCategory(Long id);

    /**
     * Finds a category by its ID.
     *
     * @param id The ID of the category to find.
     * @return The category matching the provided ID.
     */
    Category findCategory(Long id);

    /**
     * Updates a category identified by its ID.
     *
     * @param category The updated category.
     * @param id       The ID of the category to update.
     * @return The updated category.
     * @throws Exception If an error occurs during the update process.
     */
    Category update(Category category, Long id) throws Exception;


}
