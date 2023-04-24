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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/paging")
    public PageResponseDTO<?> getAllProduct(Pageable request) {
        return productService.getAllProduct(request);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDto>> allProductDTO() {
        return new ResponseEntity<>(productMapper.toProductDTOs(productRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDto> getProductDTOById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.find(id), HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProductDTO(@RequestBody @Valid Product product) throws Exception {
        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProductDTO(@RequestBody @Valid ProductDto productDto, @PathVariable(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(productService.update(productDto, id), HttpStatus.OK);
    }

    @GetMapping("/filter/desc-price")
    public ResponseEntity<List<ProductDto>> filterProductDTO() {
        return new ResponseEntity<>(productMapper.toProductDTOs(productRepository.filterHighPrice()), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    List<ProductDto> searchStoreDTO(@PathVariable("name") String name) throws Exception {
        return this.productService.search(name);
    }

    @GetMapping("/filter/{categoryId}")
    List<ProductDto> filterStoreDTO(@PathVariable("categoryId") Long categoryId) throws Exception {
        return this.productService.filter(categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws Exception {
        this.productService.delete(id);
    }

    @GetMapping("/getByStoreId/{id}")
    List<Product> getByStoreId(@PathVariable("id") Long id) throws Exception {
        return this.productRepository.getProductByStoreId(id);
    }



    //    @GetMapping("/get2")
//    public ResponseEntity<List<Product>> allProduct() {
//        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
//    }
}
