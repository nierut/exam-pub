package com.example.pub.controllers;

import com.example.pub.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drink-menu")
public class DrinkController {
    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService  =drinkService;
    }

   @GetMapping("/")
   public ResponseEntity<?> getDrinkMenu() {
        return ResponseEntity.status(200).body(drinkService.getDrinkMenu());
   }
}
