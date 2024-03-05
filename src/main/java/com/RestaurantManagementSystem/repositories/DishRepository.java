package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.DishModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Transactional
@Repository
public interface DishRepository extends JpaRepository<DishModel, Long> {
    List<DishModel> findByName(String Name);

    List<DishModel> findAllByName(String Name);
}
