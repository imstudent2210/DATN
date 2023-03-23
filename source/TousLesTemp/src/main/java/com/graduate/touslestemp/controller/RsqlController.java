package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.repository.StoreRepository;
import com.graduate.touslestemp.service.StoreService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.graduate.touslestemp.rsql.CustomRsqlVisitor;
import java.util.List;

@RestController
@RequestMapping("/rsql")
public class RsqlController {
    @Autowired
    private StoreRepository storeRepository;
    @GetMapping("/store")
    public List<Store> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Store> spec = rootNode.accept(new CustomRsqlVisitor<Store>());
        return storeRepository.findAll(spec);
    }
}
