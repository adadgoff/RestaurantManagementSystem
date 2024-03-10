package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {
    @Autowired
    private OrderRepository orderRepository;
    @Getter
    @Setter
    private ConcurrentLinkedQueue<OrderDTO> ordersToCook = new ConcurrentLinkedQueue<>();
    private final Object monitor = GLOBAL_VARIABLES.MONITOR;

    public KitchenService(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("bad count of threads").

        Thread[] cooks = new Thread[countCooks];

        log.info(" =========== KITCHEN START INITIALIZING =========== ");
        for (int i = 0; i < countCooks; i++) {
            cooks[i] = new Thread(new CookService(this, monitor, orderRepository));
            cooks[i].start();
            log.info("| COOK №{} initializing work!", i);
        }
        log.info(" ============ KITCHEN END INITIALIZING ============ ");
    }

    public void addOrder(OrderDTO orderDTO) throws InterruptedException {
        ordersToCook.add(orderDTO);
        synchronized (monitor) {
            log.info("Order with id={} was added", orderDTO.getId());
            monitor.notifyAll();
            Thread.sleep(5000);
        }
    }

    public void updateOrder(OrderDTO orderDTO) {
        // TODO: check good work. If I'm not mistaken, everything will work fine as it is.
    }
}
