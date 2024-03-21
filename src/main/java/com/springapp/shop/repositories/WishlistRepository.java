package com.springapp.shop.repositories;


import com.springapp.shop.entities.Whishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Whishlist, Long> {
}
