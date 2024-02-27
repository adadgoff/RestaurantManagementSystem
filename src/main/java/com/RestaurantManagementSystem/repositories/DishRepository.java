package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByName(String Name);
}
