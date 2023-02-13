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

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }

    public Product getProdById(Integer productId){

        Optional<Product> product = productRepository.findById(productId);

        if(!product.isPresent()){
            System.out.println("NULL PRODUCT");
        }

        return productRepository.findById(productId).get(); }

    public Optional<Product> getProductById(Integer productId){
        return  productRepository.findById(productId);
    }

    public Product updateProduct(Product newProduct){
        Product product = productRepository.findById(newProduct.getId()).get();
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setStock(newProduct.getStock());

        return productRepository.save(product);
    }

    public void deleteProductById(Integer productId){
        productRepository.deleteById(productId);
    }
    
}
