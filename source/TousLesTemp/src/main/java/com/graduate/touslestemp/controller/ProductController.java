package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.ProductDto;
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

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    /*===========================DTO v2=========================*/
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/all")
    public PageResponseDTO<?> getAllProduct(Pageable request) {
        return productService.getAllProduct(request);
    }

    @GetMapping("/get/dto")
    public ResponseEntity<List<ProductDto>> allProductDTO() {
        return new ResponseEntity<>(productMapper.toProductDTOs(productRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/get/dto/{id}")
    public ResponseEntity<ProductDto> getProductDTOById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.find(id), HttpStatus.OK);
    }

    @PostMapping("/create/dto")
    public ResponseEntity<ProductDto> createProductDTO(@RequestBody @Valid Product product) throws Exception {
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
    }

    @PutMapping("/update/dto/{id}")
    public ResponseEntity<ProductDto> updateProductDTO(@RequestBody @Valid ProductDto productDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(productService.update(productDto, id), HttpStatus.OK);
    }

    /*==========================end DTO==============================*/
}
