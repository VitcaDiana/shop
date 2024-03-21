package com.springapp.shop.services;


import com.springapp.shop.dtos.WishlistRequestDTO;
import com.springapp.shop.entities.Product;
import com.springapp.shop.entities.User;
import com.springapp.shop.entities.Whishlist;
import com.springapp.shop.entities.WishlistItem;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.ProductRepository;
import com.springapp.shop.repositories.UserRepository;
import com.springapp.shop.repositories.WIshlistItemRepository;
import com.springapp.shop.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishListService {


    private UserRepository userRepository;
    private WIshlistItemRepository wIshlistItemRepository;

    private ProductRepository productRepository;

    private WishlistRepository wishlistRepository;

    @Autowired
    public WishListService(ProductRepository productRepository, UserRepository userRepository, WIshlistItemRepository wIshlistItemRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.wIshlistItemRepository = wIshlistItemRepository;
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public Whishlist addItemToWishlist(WishlistRequestDTO wishlistRequestDTO){
        User user = userRepository.findById(wishlistRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Product product = productRepository.findById(wishlistRequestDTO.getProductId()).orElseThrow(()->new ResourceNotFoundException("product not found"));
        Whishlist whishlist = user.getWishlist();
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(product);
        wishlistItem.setWishlist(whishlist);
        whishlist.getWishlistItems().add(wishlistItem);
        return wishlistRepository.save(whishlist);

    }
}
