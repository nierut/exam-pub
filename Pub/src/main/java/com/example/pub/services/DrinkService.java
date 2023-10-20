package com.example.pub.services;


import com.example.pub.models.Drink;
import com.example.pub.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> getDrinkMenu() {
        return (List<Drink>) (drinkRepository.findAll());
    }

    public Drink getProductById(Long id) {
        Optional<Drink> drink = drinkRepository.findById(id);
        if (drink.isPresent()) {
            return drink.get();
        } else {
            throw new RuntimeException("Product not found");
        }
    }

}
