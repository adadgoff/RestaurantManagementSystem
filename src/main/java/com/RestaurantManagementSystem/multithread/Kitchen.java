package com.RestaurantManagementSystem.multithread;

import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.models.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Kitchen {

    private final ConcurrentHashMap<Long, Order> ordersQueue;
    private final int cooksNumber;
    private final Cooker[] cookers;

    public Kitchen(int cooksNumber) {
        ordersQueue = new ConcurrentHashMap<>();
        this.cooksNumber = cooksNumber;
        cookers = new Cooker[cooksNumber];

        for (int i = 0; i < cooksNumber; i++) {

        }
    }
}
