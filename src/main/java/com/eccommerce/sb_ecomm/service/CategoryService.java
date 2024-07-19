package com.eccommerce.sb_ecomm.service;

import com.eccommerce.sb_ecomm.models.Category;
import com.eccommerce.sb_ecomm.payload.CategoryDTO;
import com.eccommerce.sb_ecomm.payload.CategoryResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CategoryService {

  CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

  CategoryDTO createCategory(CategoryDTO category);

  CategoryDTO deleteCategory(Long categoryId);

  CategoryDTO updateCategory(CategoryDTO category,Long categoryId);
}
