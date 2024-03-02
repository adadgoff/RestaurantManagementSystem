package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.models.Order;
import com.RestaurantManagementSystem.multithread.Kitchen;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COUNT_COOKS);

    public void createOrder(Order order) throws IOException {

    }

    public void deleteCookingOrder() {

    }
}
