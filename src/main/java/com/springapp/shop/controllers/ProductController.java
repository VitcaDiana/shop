package com.springapp.shop.controllers;

import com.springapp.shop.dtos.ProductRequestDTO;
import com.springapp.shop.entities.Category;
import com.springapp.shop.entities.Product;
import com.springapp.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequestDTO));
    }
    @PostMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.findById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Product>> findAllByCartegoryId(@PathVariable Long categoryId) {
        List<Product> productList = productService.findByCategoryId(categoryId);
        return ResponseEntity.ok(productList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable Long id) {
        Product updateProduct = productService.updateProduct(productRequestDTO, id);
        return ResponseEntity.ok(updateProduct);


    }
}
