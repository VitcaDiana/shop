package com.springapp.shop.services;

import com.springapp.shop.entities.CartItem;
import com.springapp.shop.entities.Order;
import com.springapp.shop.entities.OrderItem;
import com.springapp.shop.entities.User;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.CartItemRepository;
import com.springapp.shop.repositories.OrderRepository;
import com.springapp.shop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartItemRepository cartItemRepository;

//    public User addOrderToUser(Long userId){
//        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));
//        Order order = new Order;
//        List<OrderItem> orderItems= new ArrayList<>();
//        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(userId);
//        orderItems = cartItems.stream()
//
    }


