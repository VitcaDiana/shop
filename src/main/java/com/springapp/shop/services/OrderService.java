package com.springapp.shop.services;

import com.springapp.shop.dtos.CartItemResponseDTO;
import com.springapp.shop.dtos.OrderItemResponseDTO;
import com.springapp.shop.entities.CartItem;
import com.springapp.shop.entities.Order;
import com.springapp.shop.entities.OrderItem;
import com.springapp.shop.entities.User;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.CartItemRepository;
import com.springapp.shop.repositories.OrderRepository;
import com.springapp.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartItemRepository cartItemRepository;

    public OrderItemResponseDTO mapFromCartitemDTOToOrderitemDTO(CartItemResponseDTO cartItem) {
        OrderItemResponseDTO orderItemDTO = new OrderItemResponseDTO();
        orderItemDTO.setId(cartItem.getId());
        orderItemDTO.setPrice(cartItem.getPrice());
        orderItemDTO.setQuantity(cartItem.getQuantity());
        orderItemDTO.setProductName(cartItem.getProductName());
        return orderItemDTO;
    }

    public Double computeTotalPrice(List<OrderItem> orderitems) {
        Optional<Double> totalPrice = orderitems.stream()
                .map(orderitem -> orderitem.getPrice())
                .reduce((sum, number) -> sum + number);

        return totalPrice.orElseThrow(() -> new ResourceNotFoundException("total price could not be computed"));

    }

    public OrderItem mapFromCartitemtoOrderitem(CartItem cartItem, Order order) {
        OrderItem orderitem = new OrderItem();
        orderitem.setQuantity(cartItem.getQuantity());
        orderitem.setProduct(cartItem.getProduct());
        orderitem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        orderitem.setOrder(order);
        return orderitem;
    }

    //Plasam o comanda pentru un utilizator (cu produsele pe care le are in cosul de cumparaturi)
    //
    //Endpoint: /orders/add/{userId}
    @Transactional
    public Order addOrderToUser() {
        //gasesc userul dupa id
        //creez o noua comanda
        //atasez comanda de utilizator
        //gasesc cartitem-urile utilizatorului dupa id
        //mut cartitem-urile in orderitem , sau le mapez
        // atasez lista de orderitem la order
        //salvez orderul
        //sterg lista de cartitem-uri dupa id utilizator
        //salvez user-ul

        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUsername(loggedInUserName).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Order order = new Order();

        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(user.getId());
        if (cartItems.size() == 0) {
            throw new ResourceNotFoundException("order cannot be placed. Cart is empty");
        }
        List<OrderItem> orderitems = getOrderitems(order, cartItems);
        order.setTotalPrice(computeTotalPrice(orderitems));
        order.setOrderItems(orderitems);
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        cartItemRepository.deleteAllByUser_Id(user.getId());
        return orderRepository.save(order);
    }

    public List<OrderItem> getOrderitems(Order order, List<CartItem> cartItems) {
        List<OrderItem> orderitems = cartItems.stream()
                .map(cartItem -> mapFromCartitemtoOrderitem(cartItem, order))
                .collect(Collectors.toList());
        return orderitems;
    }

    @Transactional
    public List<Order> viewOrders(Long userId) {
        List<Order> allOrders = orderRepository.findAllByUser_IdOrderByCreatedAt(userId);
        return allOrders;
    }


}
