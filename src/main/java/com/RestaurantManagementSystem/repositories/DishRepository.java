package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.DishModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishModel, Long> {
    List<DishModel> findAllByOrderByName();

    List<DishModel> findAllByActiveOrderByName(Boolean active);

    List<DishModel> findAllByNameOrderByName(String name);

    List<DishModel> findAllByNameAndActiveOrderByName(String name, Boolean active);
}
