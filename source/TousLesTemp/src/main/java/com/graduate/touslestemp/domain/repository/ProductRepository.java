package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
