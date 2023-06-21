package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDTO;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.mapper.ProductMapper;
import com.graduate.touslestemp.domain.repository.ProductRepository;
import com.graduate.touslestemp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @File: ProductController.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:11 AM
 * @Update: 21/6/2023
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    /**
     * Retrieves all ProductDTOs.
     *
     * @return ResponseEntity containing a list of all ProductDTOs.
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> allProductDTO() {
        return new ResponseEntity<>(productMapper.toProductDTOs(productRepository.findAll()), HttpStatus.OK);
    }

    /**
     * Retrieves a ProductDTO by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return ResponseEntity containing the ProductDTO matching the given ID.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> getProductDTOById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.find(id), HttpStatus.OK);
    }

    /**
     * Creates a new ProductDTO.
     *
     * @param product The Product object to be created.
     * @return ResponseEntity containing the created ProductDTO.
     * @throws Exception if an error occurs during the creation process.
     */

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProductDTO(@RequestBody @Valid Product product) throws Exception {
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
    }

    /**
     * Updates a ProductDTO by its ID.
     *
     * @param productDto The updated ProductDTO object.
     * @param id         The ID of the product to be updated.
     * @return ResponseEntity containing the updated ProductDTO.
     * @throws Exception if an error occurs during the update process.
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProductDTO(@RequestBody @Valid ProductDTO productDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(productService.update(productDto, id), HttpStatus.OK);
    }

    /**
     * Retrieves filtered ProductDTOs by descending price.
     *
     * @return ResponseEntity containing a list of filtered ProductDTOs.
     */
    @GetMapping("/filter/desc-price")
    public ResponseEntity<List<ProductDTO>> filterProductDTO() {
        return new ResponseEntity<>(productMapper.toProductDTOs(productRepository.filterHighPrice()), HttpStatus.OK);
    }

    /**
     * Searches for ProductDTOs by name.
     *
     * @param name The name to search for.
     * @return A list of matched ProductDTOs.
     */
    @GetMapping("/search/{name}")
    List<ProductDTO> searchStoreDTO(@PathVariable("name") String name) {
        return this.productService.search(name);
    }

    /**
     * Filters ProductDTOs by category ID.
     *
     * @param categoryId The ID of the category to filter by.
     * @return A list of filtered ProductDTOs.
     */
    @GetMapping("/filter/{categoryId}")
    List<ProductDTO> filterStoreDTO(@PathVariable("categoryId") Long categoryId) {
        return this.productService.filter(categoryId);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        this.productService.delete(id);
    }

    /**
     * Retrieves ProductDTOs by store ID.
     *
     * @param id The ID of the store.
     * @return A list of ProductDTOs belonging to the store.
     */
    @GetMapping("/getByStoreId/{id}")
    List<ProductDTO> getByStoreId(@PathVariable("id") Long id) {
        return this.productMapper.toProductDTOs(productRepository.getProductByStoreId(id));
    }
}
