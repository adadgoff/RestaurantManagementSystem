package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
