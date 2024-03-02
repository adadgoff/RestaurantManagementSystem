package com.RestaurantManagementSystem.multithread;

import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.models.Order;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
@Slf4j
public class Kitchen {
    private final ConcurrentLinkedQueue<Dish> dishesToCook;
    private final int countCooks;
    private final Cook[] cookers;

    public Kitchen(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("Неадекватное количество потоков").

        dishesToCook = new ConcurrentLinkedQueue<>();
        this.countCooks = countCooks;
        cookers = new Cook[countCooks];

        for (int i = 0; i < countCooks; i++) {
            cookers[i] = new Cook(this);
            cookers[i].start();
        }
    }

    public void addDishToCook(Dish dishToCook) {
        dishesToCook.add(dishToCook);
    }

    // TODO: другие операции с БД.
}
