package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Category;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.repository.CategoryRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.CategoryService;
import com.graduate.touslestemp.utils.ExportUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    @GetMapping("/getActivated")
    List<Category> findCategoryActivated() {
        return this.categoryRepository.findCategoryActivated();
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody @Valid Category category) throws Exception {
        category.setName(category.getName());
        return this.categoryService.save(category);
    }

    @GetMapping("/getByName/{category}")
    Category getCategoryByName(@PathVariable("category") String category) throws Exception {
        return this.categoryService.findCategory(category);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(categoryService.findCategory(id), HttpStatus.OK);
    }

    @PutMapping("/updateByName/{categoryname}")
    public Category updateCategory2(@RequestBody @Valid Category category, @PathVariable("categoryname") String categoryname) throws Exception {
        return this.categoryService.updateByName(category, categoryname);
    }
    @PutMapping("/update/{id}")
    public Category updateCategory(@RequestBody @Valid Category category, @PathVariable("id") Long id) throws Exception {
        return this.categoryService.update(category, id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) throws Exception{
       this.categoryService.deleteCategory(id);
    }
}
