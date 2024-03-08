package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

//@Transactional
@Data
@RequiredArgsConstructor
@Service
@Slf4j
public class KitchenService {
    private final ConcurrentLinkedQueue<OrderDTO> ordersToCook = new ConcurrentLinkedQueue<>();
    private Thread[] cooks;
    @Autowired
    private OrderRepository orderRepository;

    public KitchenService(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("bad count of threads").

        cooks = new Thread[countCooks];

        log.info(" =========== KITCHEN START INITIALIZING =========== ");
        for (int i = 0; i < countCooks; i++) {
            cooks[i] = new Thread(new CookService(this));
            cooks[i].start();
            log.info("| COOK №{} initializing work!", i);
        }
        log.info(" ============ KITCHEN END INITIALIZING ============ ");
    }

    public void addOrder(OrderDTO orderDTO) {
        ordersToCook.add(orderDTO);
        synchronized (cooks) {
            cooks.notifyAll();
        }
    }

    public void updateOrder(OrderDTO orderDTO) {
        // TODO: check good work. If I'm not mistaken, everything will work fine as it is.
    }
}
