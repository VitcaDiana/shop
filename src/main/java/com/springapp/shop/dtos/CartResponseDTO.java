package com.springapp.shop.dtos;

import com.springapp.shop.dtos.CartItemResponseDTO;

import java.util.List;

public class CartResponseDTO {

    private List<CartItemResponseDTO> cartItemResponseDTOS;
    private Double totalPrice;

    public CartResponseDTO(List<CartItemResponseDTO> cartItemResponseDTOS, Double totalPrice) {
        this.cartItemResponseDTOS = cartItemResponseDTOS;
        this.totalPrice = totalPrice;
    }

    public CartResponseDTO() {

    }

    public List<CartItemResponseDTO> getCartItemResponseDTOS() {
        return cartItemResponseDTOS;
    }

    public void setCartItemResponseDTOS(List<CartItemResponseDTO> cartItemResponseDTOS) {
        this.cartItemResponseDTOS = cartItemResponseDTOS;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
