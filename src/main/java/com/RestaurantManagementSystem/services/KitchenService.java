package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {
    @Autowired
    private OrderRepository orderRepository;
    @Getter
    @Setter
    private ConcurrentLinkedQueue<OrderDTO> ordersToCook = new ConcurrentLinkedQueue<>();
    private final Lock lock = new ReentrantLock();
    private final Condition ordersAvailable = lock.newCondition();

    public KitchenService(int countCooks) {
        // TODO: If bad count of threads -> Stop Program with exception("bad count of threads").

        Thread[] cooks = new Thread[countCooks];

        log.info(" =========== KITCHEN START INITIALIZING =========== ");
        for (int i = 0; i < countCooks; i++) {
            cooks[i] = new Thread(new CookService(this, lock, ordersAvailable, orderRepository));
            cooks[i].start();
            log.info("| COOK №{} initializing work!", i);
        }
        log.info(" ============ KITCHEN END INITIALIZING ============ ");
    }

    public void addOrder(OrderDTO orderDTO) throws InterruptedException {
        lock.lock();
        try {
            ordersToCook.add(orderDTO);
            ordersAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void updateOrder(OrderDTO orderDTO) {
        // TODO: check good work. If I'm not mistaken, everything will work fine as it is.
    }
}
