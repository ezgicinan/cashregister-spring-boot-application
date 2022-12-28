package com.ezgicinan.springbootapplication.service;

import com.ezgicinan.springbootapplication.model.Product;
import com.ezgicinan.springbootapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer productId){
        return  productRepository.findById(productId);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public void updateProduct(Integer productID, Product newProduct){
        Product product = productRepository.findById(productID).get();
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setStock(newProduct.getStock());

        productRepository.save(product);
    }
    
}
