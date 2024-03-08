package com.RestaurantManagementSystem.kitchen;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.models.OrderModel;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.OrderRepository;



public class Cook extends Thread {
    OrderRepository orderRepository;

    private final Kitchen kitchen;

    public Cook(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public void run() {
        while (true) {
            // Статус обновляется, когда последнее блюдо приготовилось.
            if (!kitchen.getOrdersToCook().isEmpty() && !kitchen.getOrdersToCook().peek().getCookingDishes().isEmpty()) {
                OrderDTO orderDTO = kitchen.getOrdersToCook().peek();
                DishDTO dishDTO = orderDTO.getCookingDishes().get(0);

                // Убрать блюдо из статус cooking == waiting.
                orderDTO.getCookingDishes().remove(dishDTO);
                orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));

                try {
                    Thread.sleep(dishDTO.getCookingTime());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Когда блюдо из заказа готово надо его обновить.
                orderDTO.getCookedDishes().add(dishDTO);
                OrderModel orderModel = orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));
                orderDTO = OrderMapper.INSTANCE.ToDTOFromModel(orderModel, new CycleAvoidingMappingContext());

                // Проверка, все ли блюда готовы.
                if (orderDTO.getCookedDishes().isEmpty()) {
                    orderDTO.setStatus(Status.COOKED);
                }

            } else if (!kitchen.getOrdersToCook().isEmpty() && kitchen.getOrdersToCook().peek().getCookingDishes().isEmpty()) {
                // Когда в заказе заканчиваются блюда в ожидании, надо заказ pop'нуть, но не обновлять статус.
                kitchen.getOrdersToCook().poll();
            } else {
                // Когда заказов нет, надо просто подождать.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
