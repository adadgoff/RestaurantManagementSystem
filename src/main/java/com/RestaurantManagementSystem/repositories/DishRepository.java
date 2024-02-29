package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.Dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByName(String Name);

    List<Dish> findAllByName(String Name);
}
