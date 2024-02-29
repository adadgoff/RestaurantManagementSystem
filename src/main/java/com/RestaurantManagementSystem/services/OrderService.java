package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.models.Order;
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

    // Create.
    public void createOrder(Order order) throws IOException {
        // TODO: create order-thread.
    }

    // Read.
//    public List<Order> getOrders() {
//
//    }
}
