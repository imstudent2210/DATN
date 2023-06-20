package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDTO;
import com.graduate.touslestemp.domain.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
/*
* @File:  ProductService.java com.graduate.touslestemp.service
*
* @Author: TamNLT
* @Since: 20/6/2023 11:29 PM
* @Last update: 20/6/2023
*
* */
public interface ProductService {
    List<Product> findAll();

    PageResponseDTO<?> getAllProduct(Pageable request);

    ProductDTO find(Long id);

    ProductDTO create(Product product) throws Exception;

    void delete(Long id);

    ProductDTO update(ProductDTO productDto, Long id) throws Exception;

    List<ProductDTO> filter(Long id);

    List<ProductDTO> search(String keyword);

    Product findProduct(String name);
}
