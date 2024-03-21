package com.springapp.shop.repositories;

import com.springapp.shop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
