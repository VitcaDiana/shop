package com.springapp.shop.dtos.mapper;


import com.springapp.shop.dtos.CartItemResponseDTO;
import com.springapp.shop.dtos.CartRequestDTO;
import com.springapp.shop.entities.CartItem;
import com.springapp.shop.entities.Product;
import com.springapp.shop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CartItemMapper {

        @Mapping(target = "id", ignore=true)

        public abstract CartItem mapCartRequestDTOtoCartItem(CartRequestDTO cartRequestDTO, Product product, User user);

        //cand vrem sa mapam de la un obiect la un dto, trebuie sa punem adnotarile prin care ii spunem
        // de la ce proprietate a obiectului mapam catre ce proprietate a dto-ului
        @Mapping(source = "product.id", target = "productId")
        @Mapping(source = "product.name", target = "productName")
        @Mapping(source = "product.price", target = "price")
        public abstract CartItemResponseDTO mapCartItemToCartItemDTO(CartItem cartItem);

    }
