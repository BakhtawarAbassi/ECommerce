package com.eccommerce.sb_ecomm.service;

import com.eccommerce.sb_ecomm.payload.ProductDTO;
import com.eccommerce.sb_ecomm.payload.ProductResponse;

public interface ProductService{
    ProductDTO addProduct(Long categoryId, ProductDTO product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyworf(String keyword);

    ProductDTO updateProduct(ProductDTO product, Long productId);

    ProductDTO deleteProduct(Long productId);
}
