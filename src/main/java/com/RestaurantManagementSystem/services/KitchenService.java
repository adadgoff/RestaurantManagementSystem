package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.dto.OrderDTO;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Data
@Service
@Slf4j
@NoArgsConstructor
@Transactional
public class KitchenService {
    private final ConcurrentLinkedQueue<OrderDTO> ordersToCook = new ConcurrentLinkedQueue<>();
    private final CookService[] cooks = new CookService[GLOBAL_VARIABLES.COUNT_COOKS];

    public KitchenService(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("bad count of threads").

        log.info(" =========== KITCHEN START INITIALIZING =========== ");
        for (int i = 0; i < countCooks; i++) {
            cooks[i] = new CookService(this);
            cooks[i].start();
            log.info("| COOK №{} initializing work!", i);
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
