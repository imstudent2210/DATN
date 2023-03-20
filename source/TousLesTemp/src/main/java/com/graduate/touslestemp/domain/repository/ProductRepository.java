package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);

//    @Query(value = "")

    Page<Product> findAll(Pageable pageable);
}
