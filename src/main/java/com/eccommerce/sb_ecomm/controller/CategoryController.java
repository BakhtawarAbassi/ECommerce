package com.eccommerce.sb_ecomm.controller;

import com.eccommerce.sb_ecomm.config.AppConstants;
import com.eccommerce.sb_ecomm.models.Category;
import com.eccommerce.sb_ecomm.payload.CategoryDTO;
import com.eccommerce.sb_ecomm.payload.CategoryResponse;
import com.eccommerce.sb_ecomm.service.CategoryService;
import com.eccommerce.sb_ecomm.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    // Field Injection
    @Autowired
   private CategoryService categoryService;

    //Constructor Injection
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }


    @GetMapping("/api/public/categories")
    //@RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber" ,defaultValue = AppConstants.Page_Number,required = false)Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstants.Page_Size,required = false)Integer  pageSize
            ,@RequestParam(name="sortBy",defaultValue = AppConstants.Sort_Category_By,required = false )String sortBy,
            @RequestParam(name="sortOrder",defaultValue = AppConstants.Sort_Dir,required = false)String sortOrder){
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid  @RequestBody CategoryDTO categoryDTO){
       CategoryDTO savedCategoryDTO= categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>( savedCategoryDTO,HttpStatus.OK);
    }
    @DeleteMapping("api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@Valid @PathVariable Long categoryId){
            CategoryDTO savedCategoryDTO=categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
      }


@PutMapping("/public/categories/{categoryId}")
public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                             @PathVariable Long categoryId){
    CategoryDTO savedCategory = categoryService.updateCategory(categoryDTO, categoryId);
    return new ResponseEntity<>(savedCategory, HttpStatus.OK);
}
}
