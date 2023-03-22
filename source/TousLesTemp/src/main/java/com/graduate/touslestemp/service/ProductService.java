package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product) throws Exception;
    Product findStore(String name);
    Product update(Product product, String name) throws Exception;
    void deleteProduct (Long id);
    PageResponseDTO<?> getAllProduct(Pageable request);
    /*====================== DTO==================*/

    ProductDto find(Long id);
    ProductDto create(Product product) throws Exception;
    void delete (Long id);
    ProductDto update(ProductDto productDto, Long id) throws Exception;
    /*================================================*/
//    ProductDto create(MultipartFile imageProduct, Product product) throws Exception;
}
