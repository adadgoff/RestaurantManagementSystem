package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.models.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
CRUD-operations with Dish.
 */
@Service
public class DishService {
    private List<Dish> dishes = new ArrayList<>();
    private Long ID = 0L;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dish.setId(++ID);
        dishes.add(dish);
    }

    public void deleteDish(Long id) {
        dishes.removeIf(dish -> dish.getId().equals(id));
    }

    public Dish getById(Long id) {
        for (Dish dish : dishes) {
            if (dish.getId().equals(id)) {
                return dish;
            }
        }
        return null;
    }
}
