package com.RestaurantManagementSystem.kitchen;

import com.RestaurantManagementSystem.dto.OrderDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Slf4j
@Data
public class Kitchen {
    private ConcurrentLinkedQueue<OrderDTO> ordersToCook;

    // TODO: cooking == waiting. maybe replace.
    public Kitchen(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("bad count of threads").
        ordersToCook = new ConcurrentLinkedQueue<>();
        Cook[] cooks = new Cook[countCooks];

        log.info(" =========== KITCHEN START INITIALIZING =========== ");
        for (int i = 0; i < countCooks; i++) {
            cooks[i] = new Cook(this);
            cooks[i].start();
            log.info("| Cook №{} initializing work!", i);
        }
        log.info(" ============ KITCHEN END INITIALIZING ============ ");
    }

    public void addOrder(OrderDTO orderDTO) {
        ordersToCook.add(orderDTO);
    }

    public void updateOrder(OrderDTO orderDTO) {
        // TODO: check good work. If I'm not mistaken, everything will work fine as it is.
    }
}

