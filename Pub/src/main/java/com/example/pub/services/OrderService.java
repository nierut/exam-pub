package com.example.pub.services;

import com.example.pub.models.DTOs.PlacedOrder;
import com.example.pub.models.Drink;
import com.example.pub.models.Order;
import com.example.pub.models.User;
import com.example.pub.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final DrinkService drinkService;
    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService, DrinkService drinkService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.drinkService = drinkService;
    }

    public Order placeOrder(PlacedOrder placedOrder) {
        User user = userService.getUserById(placedOrder.getUserId());
        Drink drink = drinkService.getProductById(placedOrder.getProductId());
        Integer amount = drink.getPrice()/placedOrder.getPrice();
        if (isOrderValid(user, drink, placedOrder.getPrice())) {
            userService.payForOrder(user, placedOrder.getPrice());
            return createOrder(drink.getProductName(), amount, placedOrder.getPrice(), user.getId());
        }
        else {
            throw new RuntimeException("Order is not valid");
        }
    }

    private boolean isOrderValid(User user, Drink drink, Integer price) {
        if(price > user.getPocket()) {
            return false;
        }
        if(drink.isForAdult() == true) {
            return user.isAdult();
        }
        return true;
    }

    private Order createOrder(String productName,Integer amount, Integer price, Long userId) {
        Order order = new Order(productName,amount,price,userId);
        orderRepository.save(order);
        return order;
    }
}
