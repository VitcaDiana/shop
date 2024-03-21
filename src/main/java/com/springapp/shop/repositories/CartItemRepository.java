package com.springapp.shop.repositories;


import com.springapp.shop.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
     List<CartItem> findAllByUser_Id(Long userId);
}
