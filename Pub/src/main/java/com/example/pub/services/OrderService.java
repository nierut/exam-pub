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

    public void placeOrder(PlacedOrder placedOrder) {
        if (isOrderValid(placedOrder)) {

        }
    }

    private boolean isOrderValid(PlacedOrder placedOrder) {
        User user = userService.getUserById(placedOrder.getUserId());
        Drink drink = drinkService.getProductById(placedOrder.getProductId());
        if(placedOrder.getPrice() > user.getPocket()) {
            return false;
        }
        if(drink.isForAdult() == true) {
            return user.isAdult();
        }
        return true;
    }
}
