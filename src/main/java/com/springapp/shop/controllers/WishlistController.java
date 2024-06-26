package com.springapp.shop.controllers;


import com.springapp.shop.dtos.WishlistRequestDTO;
import com.springapp.shop.entities.Whishlist;
import com.springapp.shop.entities.WishlistItem;
import com.springapp.shop.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")

public class WishlistController {

    private WishListService wishListService;

    @Autowired
    public WishlistController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/")
    public ResponseEntity<Whishlist> addToWishList(@RequestBody WishlistRequestDTO wishlistRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListService.addItemToWishlist(wishlistRequestDTO));
    }
    @GetMapping("/{userId}/items")
    public ResponseEntity<List<WishlistItem>> getUserWishlistItems(@PathVariable Long userId) {
        List<WishlistItem> wishlistItems = wishListService.getUserWishlistItems(userId);
        return ResponseEntity.ok(wishlistItems);
    }
}

