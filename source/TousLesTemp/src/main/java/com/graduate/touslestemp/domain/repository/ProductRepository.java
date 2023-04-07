package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.dto.ProductDto;
import com.graduate.touslestemp.domain.entity.Product;
import com.graduate.touslestemp.domain.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    @Query("select p from Product p where not (p.inventory = 0)  order by p.price desc")
    List<Product> filterHighPrice();
    @Query("select p from Product p where p.name like :name" )
    List<Product> searchProductByName(@Param("name") String name);

    @Query("select p from Product p join Category c where p.category.id = c.id and c.id = :id" )
    List<Product> filterStoreByCategoryId(@Param("id") Long id);
    @Query("select p from Product p join Store s where p.store.id = s.id and s.id = :id" )
    List<Product> getProductByStoreId(@Param("id") Long id);
}
