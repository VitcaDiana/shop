package com.springapp.shop.dtos;

import java.util.HashSet;

public class ProductRequestDTO {


    private Double price;
    private String name;
    private Long categoryId;
    private Integer stock;

    public ProductRequestDTO(Double price, String name, Long categoryId,Integer stock) {
        this.price = price;
        this.name = name;
        this.categoryId = categoryId;
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
