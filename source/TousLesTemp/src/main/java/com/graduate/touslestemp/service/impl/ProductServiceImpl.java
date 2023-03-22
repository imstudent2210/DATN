package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.dto.StoreDto;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.ProductMapper;
import com.graduate.touslestemp.domain.repository.ProductRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    /*================================ DTO v2==========================*/
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDto create(Product product) throws Exception {
        return (productMapper.toProductDTO(productRepository.save(product)));
    }

    @Override
    public ProductDto find(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RequestException("Not found product, id: " + id);
        }
        return (productMapper.toProductDTO(productRepository.findById(id).get()));
    }

    @Override
    public ProductDto update(ProductDto productDto, Long id) throws Exception {
        Product local = this.productRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this product id: " + id));
        productMapper.updateEntity(productDto, local);
        return (productMapper.toProductDTO(productRepository.save(local)));
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this address id: " + id)));
    }

    /*================================ end DTO==========================*/
    final ModelMapper modelMapper = new ModelMapper();

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
        return modelMapper.map(productRepository.findAll(request), PageResponseDTO.class);
    }
}
