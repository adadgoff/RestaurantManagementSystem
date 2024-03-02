package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.models.Order;
import com.RestaurantManagementSystem.multithread.Kitchen;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COUNT_COOKS);

    // Create.
    public void createOrder(Order order) {
        log.info("Creating new Order. id={}; dishes to cook={}; start time={}", order.getId(), order.getCookingDishes(), order.getStartTime());
        orderRepository.save(order);
    }

    // Read,
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Update.
    // TODO: implement update.

    // Delete.
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
