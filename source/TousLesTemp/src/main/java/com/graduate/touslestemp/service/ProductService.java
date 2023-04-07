package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.dto.StoreDto;
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
    Product findProduct(Long id);
    ProductDto create(Product product) throws Exception;
    void delete (Long id);
    ProductDto update(ProductDto productDto, Long id) throws Exception;
    List<ProductDto> filter(Long id);
    List<ProductDto> search(String keyword);


    //   ============= Upload file image ============
    Product create2( Product product, MultipartFile [] img) throws Exception;
    Product update2(Product product, Long id,MultipartFile [] img ) throws Exception;

//    void delete2(Long id) throws Exception;
}
