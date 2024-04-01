package com.springapp.shop.controllers;

import com.springapp.shop.entities.Order;
import com.springapp.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //Plasam o comanda pentru un utilizator (cu produsele pe care le are in cosul de cumparaturi)
    //
    //Endpoint: /orders/add/{userId}
    @PostMapping("/add/{userId}")
    public ResponseEntity<Order> addOrderByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.addOrderToUser(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> viewAllOrderByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.viewOrders(userId));
    }

}
