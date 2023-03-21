package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product) throws Exception;
    Product findStore(String name);
    Product update(Product product, String name) throws Exception;
    void deleteProduct (Long id);
    PageResponseDTO<?> getAllProduct(Pageable request);
}
