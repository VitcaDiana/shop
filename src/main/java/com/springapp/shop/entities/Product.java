package com.springapp.shop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    // o categorie va avea mai multe produse

    @Column
    private Double price;
    @Column
    private Integer stock;

    @ManyToOne
    @JsonBackReference("category-product")
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy="product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("whishlistitem-product")
    private List<WishlistItem> wishlistItems;
    @OneToMany(mappedBy = "product",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("cartitem-product")
    private List<CartItem> cartItems;
    @OneToMany(mappedBy = "product",cascade = {CascadeType.MERGE,CascadeType.PERSIST},orphanRemoval = true)
    @JsonManagedReference("orderitem-product")
    private List<OrderItem> orderItems;

    public Product() {
    }

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +

                '}';
    }
}
