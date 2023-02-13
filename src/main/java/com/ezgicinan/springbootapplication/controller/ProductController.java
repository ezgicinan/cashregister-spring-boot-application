package com.ezgicinan.springbootapplication.controller;

import com.ezgicinan.springbootapplication.model.Product;
import com.ezgicinan.springbootapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllProducts")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }


    @GetMapping(path = "/getProductById/{productId}")
    public ResponseEntity<Product> getProdById(@PathVariable(name = "productId") Integer productId){
        Product product = productService.getProdById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{productId}")
    public ResponseEntity<Product> updateProductById(@RequestBody Product product){
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable(name = "productId") Integer productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    /*
    @PostMapping(path = "/update/{productID}")
    public String updateProduct(@PathVariable("productID") Integer productID, @RequestBody Product product){
        productService.updateProduct(productID, product);
        return "Product (ID: " + productID + ") has been updated.";
    }
     */

      /* @PostMapping(path = "/addProduct")
    public String addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return "Product has been added successfully";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping(path = "/getAllProducts")
    public List<Product> getAllProducts() {
        try {
            List<Product> allProducts = productService.getAllProducts();
            return  allProducts;
        } catch (Exception e) {
            return null;
        }
    }
    */


}
