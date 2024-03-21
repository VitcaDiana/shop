package com.springapp.shop.controllers;


import com.springapp.shop.dtos.CartRequestDTO;
import com.springapp.shop.dtos.CartResponseDTO;
import com.springapp.shop.entities.CartItem;
import com.springapp.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
@Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping
    public ResponseEntity<CartItem> addtoWishList(@RequestBody CartRequestDTO cartRequestDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(cartRequestDTO));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> viewCart(@PathVariable Long userId){
    return ResponseEntity.ok(cartService.viewCart(userId));
    }
}
