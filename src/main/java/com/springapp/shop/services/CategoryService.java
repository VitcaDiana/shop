package com.springapp.shop.services;


import com.springapp.shop.entities.Category;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
        // indic jpa ca aceasta este o clasa de service in care voi crea
// logica de business a aplicatiei
public class CategoryService {
    private CategoryRepository categoryRepository;
    //injectez been - ul de productCategoryRepository prin constructor
    //si ma folosesc de adnotarea @autowired

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }
    //Cream o categorie
    // de exmplu categoria "watches", cu descrierea "best watches"
    //categoria "cloth" cu descrierea "man"
    //categoria "devices" cu descrierea "electrical"
    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }
    //Vedem lista cu toate categoriile de produse
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
@Transactional
    public Category updateCategory(Category category,Long id) {
        Category categoryToBeUpdated = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category not found"));
        categoryToBeUpdated.setDescription(category.getDescription());
        categoryToBeUpdated.setName(category.getName());
        categoryToBeUpdated.setProductList(category.getProductList());
        return categoryRepository.save(categoryToBeUpdated);
    }






}
