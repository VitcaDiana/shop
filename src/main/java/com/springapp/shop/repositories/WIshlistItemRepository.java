package com.springapp.shop.repositories;


import com.springapp.shop.entities.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WIshlistItemRepository extends JpaRepository<WishlistItem, Long> {
}
