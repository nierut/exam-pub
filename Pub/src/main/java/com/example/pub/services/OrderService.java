package com.example.pub.services;

import com.example.pub.models.DTOs.PlacedOrder;
import com.example.pub.models.Drink;
import com.example.pub.models.Commission;
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

    public Commission placeOrder(PlacedOrder placedOrder) {
        User user = userService.getUserById(placedOrder.getUserId());
        Drink drink = drinkService.getProductById(placedOrder.getProductId());
        Integer amount = drink.getPrice()/placedOrder.getPrice();
        if (isOrderValid(user, drink, placedOrder.getPrice())) {
            userService.payForOrder(user, placedOrder.getPrice());
            return createOrder(drink.getProductName(), amount, placedOrder.getPrice(), user);
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

    private Commission createOrder(String productName, Integer amount, Integer price, User user) {
        Commission commission = new Commission(productName,amount,price,user);
        orderRepository.save(commission);
        return commission;
    }
}
