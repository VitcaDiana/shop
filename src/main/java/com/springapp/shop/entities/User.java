package com.springapp.shop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    @Column
    private String username;


    @Column
    private String password;



    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-whishlist")
    private Whishlist wishlist;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL},orphanRemoval = true)
    @JsonManagedReference("order-user")
    private List<Order> orders;

    public User()

    {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Whishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Whishlist wishlist) {
        this.wishlist = wishlist;
    }


}
