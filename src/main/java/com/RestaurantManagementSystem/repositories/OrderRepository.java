package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
