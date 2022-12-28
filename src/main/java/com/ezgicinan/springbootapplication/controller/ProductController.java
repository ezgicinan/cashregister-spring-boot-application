package com.ezgicinan.springbootapplication.controller;

import com.ezgicinan.springbootapplication.model.Product;
import com.ezgicinan.springbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/getAllProducts")
    public List<Product> getAllProducts() {
        try {
            List<Product> allProducts = productService.getAllProducts();
            return  allProducts;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(path = "/addProduct")
    public String addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return "Product has been added successfully";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping(path = "/update/{productID}")
    public String updateProduct(@PathVariable("productID") Integer productID, @RequestBody Product product){
        productService.updateProduct(productID, product);
        return "Product (ID: " + productID + ") has been updated.";
    }


}
