package com.eccommerce.sb_ecomm.repository;

import com.eccommerce.sb_ecomm.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
