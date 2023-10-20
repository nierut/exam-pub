package com.example.pub.controllers;

import com.example.pub.models.DTOs.PlacedOrder;
import com.example.pub.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<?> placeOrder(@RequestBody PlacedOrder placedOrder) {
        try {
            return ResponseEntity.status(200).body(orderService.placeOrder(placedOrder));
        } catch (Exception exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }

    @GetMapping("/summary/all")
    public ResponseEntity<?> getCompleteCommissionSummary() {
        return ResponseEntity.status(200).body(orderService.getSummaryAllDTOs());
    }
}
