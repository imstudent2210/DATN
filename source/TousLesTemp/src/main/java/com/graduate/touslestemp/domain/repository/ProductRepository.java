package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);
    Page<Product> findAll(Pageable pageable);
    @Query("select p from Product p where not (p.inventory = 0)  order by p.price desc")
    List<Product> filterHighPrice();
//    @Query("select p from Product p")
//    Page<Product> pageProduct(Pageable pageable);

}
