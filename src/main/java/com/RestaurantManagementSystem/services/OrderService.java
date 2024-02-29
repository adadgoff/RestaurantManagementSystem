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
    // TODO: implement.
    private final OrderRepository orderRepository;
    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COOKS_NUMBER);

    public void createOrder(Order order) throws IOException {

    }

    // Delete. Delete order from ThreadPull.
    public void deleteExecutingOrder() {

    }

    // Delete. Delete order from DataBase.
    public void deleteFinishedOrder() {

    }
}
