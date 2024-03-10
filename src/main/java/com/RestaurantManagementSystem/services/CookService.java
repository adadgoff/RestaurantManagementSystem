package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.models.OrderModel;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CookService implements Runnable {
    private final KitchenService kitchenService;
    private final Object monitor;
    private final OrderRepository orderRepository;

    public void cookDish() throws InterruptedException {
        OrderDTO orderToCook = getOrderWithWaitingDishes();
        log.info("| {}: Take order with id={}", Thread.currentThread(), orderToCook.getId());

        DishDTO dish = orderToCook.getWaitingDishes().poll();
        log.info("| {}: Take dish with id={}", Thread.currentThread(), Objects.requireNonNull(dish).getId());

        orderToCook = updateOrderWaitingDish(orderToCook, dish);
        log.info("| {}: Wait -> Cook | dish with id={}", Thread.currentThread(), Objects.requireNonNull(dish).getId());

        Thread.sleep(Objects.requireNonNull(dish).getCookingTime());

        updateOrderCookingDish(orderToCook, dish);
        log.info("| {}: Cook -> Cooked | dish with id={}", Thread.currentThread(), Objects.requireNonNull(dish).getId());
    }

    public OrderDTO getOrderWithWaitingDishes() throws InterruptedException {
        synchronized (monitor) {
            while (kitchenService.getOrdersToCook().isEmpty() || kitchenService.getOrdersToCook().stream().allMatch(orderDTO -> orderDTO.getWaitingDishes().isEmpty())) {
                monitor.wait();
            }
            return kitchenService.getOrdersToCook().stream().filter(orderDTO -> !orderDTO.getWaitingDishes().isEmpty()).findFirst().orElseThrow();
        }
    }

    public OrderDTO updateOrderWaitingDish(OrderDTO orderToCook, DishDTO waitingDish) {
        // dishDTO переходит из waiting в cooking.
        synchronized (monitor) {
            orderToCook.getWaitingDishes().remove(waitingDish);
            orderToCook.getCookingDishes().add(waitingDish);
            OrderModel orderModel = orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderToCook, new CycleAvoidingMappingContext()));
            return OrderMapper.INSTANCE.ToDTOFromModel(orderModel, new CycleAvoidingMappingContext());
        }
    }

    public void updateOrderCookingDish(OrderDTO orderToCook, DishDTO cookingDish) {
        // dishDTO переходит из cooking в cooked.
        synchronized (monitor) {
            orderToCook.getCookingDishes().remove(cookingDish);
            orderToCook.getCookedDishes().add(cookingDish);
            orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderToCook, new CycleAvoidingMappingContext()));
            // TODO: implement status.
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                log.info("| {} | Chilling", Thread.currentThread());
                cookDish();
                log.info("| {} | Waking", Thread.currentThread());
            }
        } catch (InterruptedException e) {
            log.error("CookService interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}