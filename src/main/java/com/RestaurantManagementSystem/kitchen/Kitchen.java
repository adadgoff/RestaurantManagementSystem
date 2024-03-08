package com.RestaurantManagementSystem.kitchen;

import com.RestaurantManagementSystem.dto.OrderDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class Kitchen {
    private ConcurrentLinkedQueue<OrderDTO> ordersToCook;

    // TODO: cooking == waiting. maybe replace.
    public Kitchen(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("bad count of threads").
        ordersToCook = new ConcurrentLinkedQueue<>();
        Cook[] cooks = new Cook[countCooks];

        for (int i = 0; i < countCooks; i++) {
            cooks[i] = new Cook(this);
            cooks[i].start();
        }
    }

    public void addOrder(OrderDTO orderDTO) {
        ordersToCook.add(orderDTO);
    }


}
