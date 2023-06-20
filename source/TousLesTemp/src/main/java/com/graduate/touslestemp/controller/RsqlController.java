package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.dto.ProductDTO;
import com.graduate.touslestemp.domain.dto.StoreDTO;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.mapper.ProductMapper;
import com.graduate.touslestemp.domain.mapper.StoreMapper;
import com.graduate.touslestemp.domain.repository.ProductRepository;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
* @File:  RsqlController.java com.graduate.touslestemp.controller
*
* @Author: TamNLT
* @Since: 20/6/2023 11:10 PM
* @Last update: 20/6/2023
*
* */

@RestController
@RequestMapping("/rsql")
public class RsqlController {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/store")
    public List<StoreDTO> findStoreByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Store> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return storeMapper.toStoreDTOs(storeRepository.findAll(spec));
    }

    @GetMapping("/product")
    public List<ProductDTO> findProductByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return productMapper.toProductDTOs(productRepository.findAll(spec));
    }
}
