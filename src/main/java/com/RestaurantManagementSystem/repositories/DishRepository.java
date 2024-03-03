package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.DishModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface DishRepository extends JpaRepository<DishModel, Long> {
    List<DishModel> findByName(String Name);

    List<DishModel> findAllByName(String Name);
}
