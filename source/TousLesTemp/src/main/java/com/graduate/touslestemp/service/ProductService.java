package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    PageResponseDTO<?> getAllProduct(Pageable request);
    ProductDto find(Long id);
    ProductDto create(Product product) throws Exception;
    void delete (Long id);
    ProductDto update(ProductDto productDto, Long id) throws Exception;
    List<ProductDto> filter(Long id);
    List<ProductDto> search(String keyword);
    Product findProduct(String name);
}
