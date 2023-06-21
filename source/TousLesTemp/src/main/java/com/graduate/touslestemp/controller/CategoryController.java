package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.repository.CategoryRepository;
import com.graduate.touslestemp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @File: CategoryController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:11 AM
 * @Update: 21/6/2023
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories.
     */
    @GetMapping("/get")
    List<Category> allCategory() {
        return this.categoryRepository.findAll();
    }

    /**
     * Retrieves all activated categories.
     *
     * @return A list of activated categories.
     */
    @GetMapping("/getActivated")
    List<Category> findCategoryActivated() {
        return this.categoryRepository.findCategoryActivated();
    }

    /**
     * Creates a new category.
     *
     * @param category The category object to be created.
     * @return The created category.
     * @throws Exception if an error occurs during the creation process.
     */
    @PostMapping("/create")
    public Category createCategory(@RequestBody @Valid Category category) throws Exception {
        category.setName(category.getName());
        return this.categoryService.save(category);
    }

    /**
     * Retrieves a category by its name.
     *
     * @param category The name of the category to retrieve.
     * @return The category matching the given name.
     */
    @GetMapping("/getByName/{category}")
    Category getCategoryByName(@PathVariable("category") String category) {
        return this.categoryService.findCategory(category);

    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The category matching the given ID.
     */

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(categoryService.findCategory(id), HttpStatus.OK);
    }

    /**
     * Updates a category by its name.
     *
     * @param category     The updated category object.
     * @param categoryname The name of the category to be updated.
     * @return The updated category.
     * @throws Exception if an error occurs during the update process.
     */
    @PutMapping("/updateByName/{categoryname}")
    public Category updateCategory2(@RequestBody @Valid Category category, @PathVariable("categoryname") String categoryname) throws Exception {
        return this.categoryService.updateByName(category, categoryname);
    }

    /**
     * Updates a category by its ID.
     *
     * @param category The updated category object.
     * @param id       The ID of the category to be updated.
     * @return The updated category.
     * @throws Exception if an error occurs during the update process.
     */
    @PutMapping("/update/{id}")
    public Category updateCategory(@RequestBody @Valid Category category, @PathVariable("id") Long id) throws Exception {
        return this.categoryService.update(category, id);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to be deleted.
     */

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        this.categoryService.deleteCategory(id);
    }
}
