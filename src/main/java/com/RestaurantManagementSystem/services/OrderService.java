package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.DishMapper;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.models.DishModel;
import com.RestaurantManagementSystem.models.OrderModel;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.DishRepository;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final DishMapper dishMapper;
    private final OrderMapper orderMapper;
    //    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COUNT_COOKS);

    // Read all.
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::ToDTOFromModel).collect(Collectors.toList());
    }

    // Create. TODO: implement many dishes in order.
    public void createOrder(Long idDishToCook, OrderDTO orderDTO, Principal principal) {
        orderDTO.setCookingDishes(
                Collections.singletonList(
                        dishMapper.ToDTOFromModel(
                                dishRepository.findById(idDishToCook).orElse(null),
                                new CycleAvoidingMappingContext())
                )
        );
        orderDTO.setCookedDishes(new ArrayList<>());
        orderDTO.setCost(orderDTO.getCookingDishes().stream().mapToLong(DishDTO::getPrice).sum());
        orderDTO.setStartTime(Instant.now());
        orderDTO.setStatus(Status.COOKING);

        OrderModel orderModel = orderMapper.ToModelFromDTO(orderDTO);
        orderRepository.save(orderModel);
        log.info("Creating new Order. id={}; dishes to cook ids={}; dishes names={}; status={}; user email={}",
                orderModel.getId(),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getStatus(),
                orderModel.getUser().getEmail()
        );
    }

    // Read.
    public OrderDTO getOrderById(Long id) {
        return orderMapper.ToDTOFromModel(orderRepository.findById(id).orElse(null));
    }

    // Update.
    // TODO: implement update.

    // Delete.
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
