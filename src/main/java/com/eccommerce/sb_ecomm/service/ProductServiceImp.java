package com.eccommerce.sb_ecomm.service;

import com.eccommerce.sb_ecomm.exceptions.ResourceNotFoundException;
import com.eccommerce.sb_ecomm.models.Category;
import com.eccommerce.sb_ecomm.models.Product;
import com.eccommerce.sb_ecomm.payload.ProductDTO;
import com.eccommerce.sb_ecomm.payload.ProductResponse;
import com.eccommerce.sb_ecomm.repository.CategoryRepository;
import com.eccommerce.sb_ecomm.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDto) {
   Category category= categoryRepository.findById(categoryId)
         .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
    Product product=modelMapper.map(productDto,Product.class);
    product.setImage("default.png");
   product.setCategory(category);
    double specialPrice= product.getPrice()
            - ((product.getDiscount()*0.01) *product.getPrice());
    product.setSpecialPrice(specialPrice);
    Product savedProduct=productRepository.save(product);
 return modelMapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
      List<Product> products= productRepository.findAll();
      List<ProductDTO> productDTO=products.stream()
              .map(product -> modelMapper.map(product,ProductDTO.class))
              .toList();
      ProductResponse productResponse=new ProductResponse();
      productResponse.setContent(productDTO);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        List<Product> products=productRepository.findByCategoryOrderByPriceAsc(category);

        List<ProductDTO> productDTO=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDTO);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyworf(String keyword) {
        List<Product> products=productRepository.findByProductNameLikeIgnoreCase(keyword);

        List<ProductDTO> productDTO=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponse productResponse=new ProductResponse();
        productResponse.setContent(productDTO);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
      Product productFromDatabase= productRepository.findById(productId)
              .orElseThrow(()->new ResourceNotFoundException("Product","productID",productId));
      Product product=modelMapper.map(productFromDatabase,Product.class);
      productFromDatabase.setProductName(product.getProductName());
      productFromDatabase.setPrice(product.getPrice());
   // productFromDatabase.setImage(product.getImage());
      productFromDatabase.setDescription(product.getDescription());
      productFromDatabase.setQuantity(product.getQuantity());
      productFromDatabase.setDiscount(product.getDiscount());
      double specialPrice=product.getPrice() - ((product.getDiscount() *0.01) *product.getPrice());
      productFromDatabase.setSpecialPrice(specialPrice);
      Product savedProduct= productRepository.save(productFromDatabase);

        return modelMapper.map(productFromDatabase,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
       Product product= productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","productId",productId));

            productRepository.delete(product);
        return modelMapper.map(product,ProductDTO.class);
    }
}
