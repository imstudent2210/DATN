package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.repository.ProductRepository;
import com.graduate.touslestemp.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product save(Product product) throws Exception {
        return null;
    }

    @Override
    public Product findStore(String name) {
        return null;
    }

    @Override
    public Product update(Product product, String name) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public PageResponseDTO<?> getAllProduct(Pageable request) {
        return null;
    }
}
