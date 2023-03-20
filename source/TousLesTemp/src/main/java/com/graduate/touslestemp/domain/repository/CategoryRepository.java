package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Address;
import com.graduate.touslestemp.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String category);
}
