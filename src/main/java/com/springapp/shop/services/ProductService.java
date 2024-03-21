package com.springapp.shop.services;

import com.springapp.shop.dtos.ProductRequestDTO;
import com.springapp.shop.entities.Category;
import com.springapp.shop.entities.Product;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.CategoryRepository;
import com.springapp.shop.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(ProductRequestDTO productRequestDTO) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category not found"));
        Product productToBeSaved = new Product();
        productToBeSaved.setName(productRequestDTO.getName());
        productToBeSaved.setPrice(productRequestDTO.getPrice());
        productToBeSaved.setCategory(category);
        if (productToBeSaved.getStock() == null) {
            productToBeSaved.setStock(1);
        } else {
            productToBeSaved.setStock(productToBeSaved.getStock() + 1);
        }
        return productRepository.save(productToBeSaved);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product not found"));

    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

    @Transactional
    public Product updateProduct(ProductRequestDTO productRequestDTO, Long id) {
        Product productUpdated = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        if (productRequestDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("category not found"));
            productUpdated.setCategory(category);
        }

        productUpdated.setPrice(productRequestDTO.getPrice());
        productUpdated.setName(productRequestDTO.getName());
        productUpdated.setStock(productRequestDTO.getStock());
        return productRepository.save(productUpdated);
    }
}
