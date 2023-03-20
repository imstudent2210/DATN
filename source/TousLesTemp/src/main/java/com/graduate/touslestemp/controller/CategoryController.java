package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.repository.CategoryRepository;
import com.graduate.touslestemp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    List<Category> allCategory() {
        return this.categoryRepository.findAll();
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody @Valid Category category) throws Exception {
        category.setName(category.getName());
        return this.categoryService.save(category);
    }

    @GetMapping("/get/{category}")
    Category getCategoryByName(@PathVariable("category") String category) throws Exception {
        return this.categoryService.findCategory(category);

    }

    @PutMapping("/update/{categoryname}")
    public Category updateCategory(@RequestBody @Valid Category category, @PathVariable("categoryname") String categoryname) throws Exception {
        return this.categoryService.update(category, categoryname);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) throws Exception{
       this.categoryService.deleteCategory(id);
    }
}
