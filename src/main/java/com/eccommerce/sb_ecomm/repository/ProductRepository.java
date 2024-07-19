package com.eccommerce.sb_ecomm.repository;

import com.eccommerce.sb_ecomm.models.Category;
import com.eccommerce.sb_ecomm.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryOrderByPriceAsc(Category category);

    //Like mean pattern matching
    List<Product> findByProductNameLikeIgnoreCase(String keyword);
}
