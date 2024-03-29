package com.springapp.shop.services;

import com.springapp.shop.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private OrderItemRepository orderitemRepository;

    @Autowired

    public OrderItemService(OrderItemRepository orderitemRepository) {
        this.orderitemRepository = orderitemRepository;
    }
}
