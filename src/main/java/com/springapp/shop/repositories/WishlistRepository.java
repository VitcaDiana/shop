package com.springapp.shop.repositories;


import com.springapp.shop.entities.CartItem;
import com.springapp.shop.entities.Whishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Whishlist, Long> {
    List<Whishlist> findAllByUser_Id(Long userId);
}
