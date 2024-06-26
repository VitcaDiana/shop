package com.springapp.shop.repositories;


import com.springapp.shop.entities.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WIshlistItemRepository extends JpaRepository<WishlistItem, Long> {

}
