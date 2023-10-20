package com.example.pub.services;

import com.example.pub.models.DTOs.SummaryAllDTO;
import com.example.pub.models.DTOs.PlacedOrder;
import com.example.pub.models.DTOs.SummaryByUserDTO;
import com.example.pub.models.DTOs.SummaryByProductDTO;
import com.example.pub.models.Drink;
import com.example.pub.models.Commission;
import com.example.pub.models.User;
import com.example.pub.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Integer amount = drink.getPrice() / placedOrder.getPrice();
        if (isOrderValid(user, drink, placedOrder.getPrice())) {
            userService.payForOrder(user, placedOrder.getPrice());
            return createOrder(drink.getProductName(), amount, placedOrder.getPrice(), user);
        } else {
            throw new RuntimeException("Order is not valid");
        }
    }

    private boolean isOrderValid(User user, Drink drink, Integer price) {
        if (price > user.getPocket()) {
            return false;
        }
        if (drink.isForAdult() == true) {
            return user.isAdult();
        }
        return true;
    }

    private Commission createOrder(String productName, Integer amount, Integer price, User user) {
        Commission commission = new Commission(productName, amount, price, user);
        orderRepository.save(commission);
        return commission;
    }

    public List<SummaryAllDTO> getSummaryAllDTOs() {
        List<Commission> commissions = (List<Commission>) (orderRepository.findAll());
        return convertCommissionsToSummaryAllDTOs(commissions);
    }

    private List<SummaryAllDTO> convertCommissionsToSummaryAllDTOs(List<Commission> commissions) {
        List<Drink> drinks = drinkService.getDrinkMenu();
        List<SummaryAllDTO> summary = new ArrayList<>();
        for (int i = 0; i < drinks.size(); i++) {
            summary.add(createSummaryAllDTO(drinks.get(i)));
        }
        return summary;
    }

    private SummaryAllDTO createSummaryAllDTO(Drink drink) {
        List<Commission> commissions = orderRepository.getCommissionsByProductName(drink.getProductName());
        Integer amountSum = 0;
        Integer priceSum = 0;
        for (int i = 0; i < commissions.size(); i++) {
            amountSum += commissions.get(i).getAmount();
            priceSum += commissions.get(i).getPrice();
        }
        SummaryAllDTO summaryForOneDrink = new SummaryAllDTO(drink.getProductName(), amountSum, drink.getPrice(), priceSum);
        return summaryForOneDrink;
    }


    public List<SummaryByProductDTO> getCommissionSummaryByProduct(Long productId) {
        String productName = drinkService.getProductById(productId).getProductName();
        List<Commission> commissions = orderRepository.getCommissionsByProductName(productName);
        List<SummaryByProductDTO> summary = new ArrayList<>();
        for (int i = 0; i < commissions.size(); i++) {
            summary.add(convertCommissionToSummaryByProductDTO(commissions.get(i)));
        }
        return summary;
    }

    private SummaryByProductDTO convertCommissionToSummaryByProductDTO(Commission commission) {
        Long userId = commission.getUser().getId();
        return new SummaryByProductDTO(userId, commission);
    }

    public List<SummaryByUserDTO> getCommissionsByUser() {
        List<Commission> commissions = (List<Commission>) (orderRepository.findAll());
        List<SummaryByUserDTO> summary = new ArrayList<>();
        for (int i = 0; i < commissions.size(); i++) {
            summary.add(convertCommissionToSummaryByUserDTO(commissions.get(i)));
        }
        return summary;
    }

    private SummaryByUserDTO convertCommissionToSummaryByUserDTO(Commission commission) {
        Long userId = commission.getUser().getId();
        return new SummaryByUserDTO(userId, commission);
    }
}
