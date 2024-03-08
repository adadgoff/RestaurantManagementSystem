package com.RestaurantManagementSystem.kitchen;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Cook extends Thread {
    private final Kitchen kitchen;

    private final OrderRepository orderRepository;

    public Cook(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public void run() {
        // Статус обновляется, когда все блюда заказы приготовились.
        while (true) {
            if (!kitchen.getOrdersToCook().isEmpty() && kitchen.getOrdersToCook().stream().anyMatch(orderDTO -> !orderDTO.getWaitingDishes().isEmpty())) {
                // Повар выбирает первое ожидающее блюдо в очереди заказов по их порядку.
                for (OrderDTO orderDTO : kitchen.getOrdersToCook()) {
                    for (DishDTO dishDTO : orderDTO.getWaitingDishes()) {
                        // Блюдо переходит в готовящиеся блюда.
                        orderDTO.getWaitingDishes().remove(dishDTO);
                        orderDTO.getCookingDishes().add(dishDTO);
                        orderDTO = OrderMapper.INSTANCE.ToDTOFromModel(
                                orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext())),
                                new CycleAvoidingMappingContext());

                        // Повар готовит блюдо.
                        try {
                            log.info("COOK: Start cook dish id={}", dishDTO.getId());
                            Thread.sleep(dishDTO.getCookingTime());
                        } catch (InterruptedException e) {
                            log.info("COOK: InterruptedException");
                        }

                        // После приготовления блюда, блюдо переходит в приготовленные блюда.
                        orderDTO.getCookingDishes().remove(dishDTO);
                        orderDTO.getCookedDishes().add(dishDTO);
                        orderDTO = OrderMapper.INSTANCE.ToDTOFromModel(
                                orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext())),
                                new CycleAvoidingMappingContext());

                        // Если нет ожидающих и готовящихся блюд в заказе, статус меняется на `COOKED`.
                        if (orderDTO.getWaitingDishes().isEmpty() && orderDTO.getCookingDishes().isEmpty()) {
                            orderDTO.setStatus(Status.COOKED);
                            orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));
                        }

                        break;
                    }
                }
            } else {
                // Если блюд нет, просто подождать секунду.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.info("COOK: InterruptedException");
                }
            }
        }
    }
}
