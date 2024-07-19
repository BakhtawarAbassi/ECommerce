package com.eccommerce.sb_ecomm.controller;

import com.eccommerce.sb_ecomm.models.Product;
import com.eccommerce.sb_ecomm.payload.ProductDTO;
import com.eccommerce.sb_ecomm.payload.ProductResponse;
import com.eccommerce.sb_ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/categories/{categoryId}/product")
public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO,
                                             @PathVariable Long categoryId){
      ProductDTO savedProductDTO=  productService.addProduct(categoryId,productDTO);
      return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProduct(){
        ProductResponse productResponse= productService.getAllProducts();
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @GetMapping("admin/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getAllProduct(@PathVariable Long categoryId){
        ProductResponse productResponse= productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("admin/product/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword){
        ProductResponse productResponse= productService.searchProductByKeyworf('%' + keyword+'%');
        return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
    }
    @PutMapping("/admim/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                    @PathVariable Long productId){
         ProductDTO updatedProductDTO= productService.updateProduct(productDTO,productId);
        return new ResponseEntity<>(updatedProductDTO,HttpStatus.OK);
    }
    @DeleteMapping("/admin/products/{productId}")
    public  ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId){

          ProductDTO productDTO= productService.deleteProduct(productId);

        return  new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
}
