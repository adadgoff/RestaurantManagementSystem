package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public List<Dish> listDishes(String name) {
        if (name != null) {
            return dishRepository.findByName(name);
        }
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    public void addDish(Dish dish) {
        log.info("Adding new {}", dish);
        dishRepository.save(dish);
    }
}
