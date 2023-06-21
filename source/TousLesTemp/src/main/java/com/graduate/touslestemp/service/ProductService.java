package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDTO;
import com.graduate.touslestemp.domain.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @File: ProductService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:30 AM
 * @Update: 21/6/2023
 */
public interface ProductService {
    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    List<Product> findAll();

    /**
     * Retrieves a page of products based on the provided pageable request.
     *
     * @param request The pageable request specifying the page size and number.
     * @return A page response DTO containing the requested products.
     */
    PageResponseDTO<?> getAllProduct(Pageable request);

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product DTO representing the retrieved product.
     */
    ProductDTO find(Long id);

    /**
     * Creates a new product.
     *
     * @param product The product to be created.
     * @return The product DTO representing the created product.
     * @throws Exception If an error occurs during the creation process.
     */
    ProductDTO create(Product product) throws Exception;

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    void delete(Long id);

    /**
     * Updates a product identified by its ID.
     *
     * @param productDto The updated product DTO.
     * @param id         The ID of the product to update.
     * @return The product DTO representing the updated product.
     * @throws Exception If an error occurs during the update process.
     */
    ProductDTO update(ProductDTO productDto, Long id) throws Exception;

    /**
     * Filters products based on the provided ID.
     *
     * @param id The ID used for filtering the products.
     * @return A list of product DTOs matching the filtering criteria.
     */
    List<ProductDTO> filter(Long id);

    /**
     * Searches for products based on the provided keyword.
     *
     * @param keyword The keyword to search for in product names.
     * @return A list of product DTOs matching the search criteria.
     */
    List<ProductDTO> search(String keyword);

    /**
     * Finds a product by its name.
     *
     * @param name The name of the product to find.
     * @return The product matching the provided name.
     */
    Product findProduct(String name);
}
