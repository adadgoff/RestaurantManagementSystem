package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CookService implements Runnable {
    private final KitchenService kitchenService;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void run() {
        while (!kitchenService.getOrdersToCook().isEmpty()) {
            // Необходимо выбрать первое блюдо из заказа, в котором есть ожидающие блюда.
            for (OrderDTO orderDTO : kitchenService.getOrdersToCook()) {
                if (!orderDTO.getWaitingDishes().isEmpty()) {
                    DishDTO dishDTO = orderDTO.getWaitingDishes().poll();

                    orderDTO.getCookingDishes().add(dishDTO);
                    orderDTO = OrderMapper.INSTANCE.ToDTOFromModel(
                            orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext())),
                            new CycleAvoidingMappingContext());

                    try {
                        Thread.sleep(dishDTO.getCookingTime());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    orderDTO.getCookedDishes().add(orderDTO.getCookingDishes().poll());
                    if (orderDTO.getWaitingDishes().isEmpty() && orderDTO.getCookingDishes().isEmpty()) {
                        orderDTO.setStatus(Status.COOKED);
                    }
                    orderDTO = OrderMapper.INSTANCE.ToDTOFromModel(
                            orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext())),
                            new CycleAvoidingMappingContext());
                }
            }
        }
//        try {
//            wait();
//            notify();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
