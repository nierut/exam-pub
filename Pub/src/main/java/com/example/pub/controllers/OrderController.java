package com.example.pub.controllers;

import com.example.pub.models.DTOs.PlacedOrder;
import com.example.pub.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/buy")
    public ResponseEntity<?> placeOrder(@RequestBody PlacedOrder placedOrder) {
        try {
            return ResponseEntity.status(200).body(orderService.placeOrder(placedOrder));
        } catch (Exception exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }

    @PreAuthorize("hasRole('BARMAN')")
    @GetMapping("/summary/all")
    public ResponseEntity<?> getCompleteCommissionSummary() {
        return ResponseEntity.status(200).body(orderService.getSummaryAllDTOs());
    }

    @PreAuthorize("hasRole('BARMAN')")
    @GetMapping("summary/{productId}")
    public ResponseEntity<?> getCommissionSummaryByProduct(@PathVariable Long productId) {
        try {
            return ResponseEntity.status(200).body(orderService.getCommissionSummaryByProduct(productId));
        } catch (Exception exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }

    @PreAuthorize("hasRole('BARMAN')")
    @GetMapping("/summary/user")
    public ResponseEntity<?> getCommissionsByUser() {
        return ResponseEntity.status(200).body(orderService.getCommissionsByUser());
    }

}
