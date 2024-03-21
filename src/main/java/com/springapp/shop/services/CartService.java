package com.springapp.shop.services;

import com.springapp.shop.dtos.CartItemResponseDTO;
import com.springapp.shop.dtos.CartRequestDTO;
import com.springapp.shop.dtos.CartResponseDTO;
import com.springapp.shop.dtos.mapper.CartItemMapper;
import com.springapp.shop.entities.CartItem;
import com.springapp.shop.entities.Product;
import com.springapp.shop.entities.User;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.CartItemRepository;
import com.springapp.shop.repositories.ProductRepository;
import com.springapp.shop.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CartItemMapper cartItemMapper;

    @Autowired
    public CartService(CartItemMapper cartItemMapper,CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Transactional
    public CartItem addToCart(CartRequestDTO cartRequestDTO) {
        User user = userRepository.findById(cartRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Product product = productRepository.findById(cartRequestDTO.getProductId()).orElseThrow(()->new ResourceNotFoundException("product not found"));
        if (product.getStock()<cartRequestDTO.getQuantity()|| product.getStock()==null){
            throw  new ResourceNotFoundException("out of stock");
        }
        CartItem cartItem =cartItemMapper.mapCartRequestDTOtoCartItem(cartRequestDTO, product, user);
//        CartItem cartItem = new CartItem();
//        cartItem.setProduct(product);
//        cartItem.setUser(user);
//        cartItem.setQuantity(cartRequestDTO.getQuantity());
        return cartItemRepository.save(cartItem);
    }



    public CartResponseDTO viewCart(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(userId);
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        List<CartItemResponseDTO> cartItemResponseDTOS = cartItems.stream()
                .map(cartItem -> cartItemMapper.mapCartItemToCartItemDTO(cartItem))
                .collect(Collectors.toList());
        cartResponseDTO.setTotalPrice(computeTotalPrice(cartItems));
        cartResponseDTO.setCartItemResponseDTOS(cartItemResponseDTOS);

        return cartResponseDTO;


    }

    public Double computeTotalPrice(List<CartItem> cartItems) {
        Optional<Double> totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice())
                .reduce((sum, number) -> sum + number);
        return totalPrice.orElseThrow(() -> new ResourceNotFoundException("total price could not be computed"));
    }

    public CartItemResponseDTO mapCartItemToCartItemResponseDTO(CartItem cartItem) {
        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setId(cartItem.getId());
        cartItemResponseDTO.setProductId(cartItem.getProduct().getId());
        cartItemResponseDTO.setProductName(cartItem.getProduct().getName());
        cartItemResponseDTO.setPrice(cartItem.getProduct().getPrice());
        cartItemResponseDTO.setQuantity(cartItem.getQuantity());

        return cartItemResponseDTO;

    }
}
