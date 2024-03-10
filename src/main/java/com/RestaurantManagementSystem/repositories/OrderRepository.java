package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findAllByUserEmailOrderByStartTime(String user_email);
}
