package com.RestaurantManagementSystem.multithread;

import com.RestaurantManagementSystem.models.Dish;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Cook extends Thread {
    private final Kitchen kitchen;

    @Override
    public void run() {
        while (true) {
            synchronized (kitchen.getDishesToCook()) {
                while (kitchen.getDishesToCook().isEmpty()) {
                    try {
                        kitchen.getDishesToCook().wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                Dish dishToCook = kitchen.getDishesToCook().poll();
                if (dishToCook != null) {
                    try {
                        Thread.sleep(dishToCook.getCookingTime());
                        // TODO: Обновить БД.
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
