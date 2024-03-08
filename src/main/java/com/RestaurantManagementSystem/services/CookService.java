package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.repositories.OrderRepository;
import com.RestaurantManagementSystem.repositories.OrderRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CookService extends Thread {
    private final KitchenService kitchenService;
    private final OrderRepository orderRepository = new OrderRepositoryImpl();  // TODO: better to refactor `new OrderRepositoryImpl`.

    @Override
    @Transactional
    public void run() {

    }
}
