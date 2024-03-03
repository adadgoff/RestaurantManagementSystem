package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.models.DishModel;
import com.RestaurantManagementSystem.models.OrderModel;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import com.RestaurantManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final OrderMapper orderMapper;
    //    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COUNT_COOKS);

    // Read all.
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::ToDTOFromModel).collect(Collectors.toList());
    }

    // Create. TODO: implement many dishes in order.
    public void createOrder(Principal principal, OrderDTO orderDTO) {
        OrderModel orderModel = orderMapper.ToModelFromDTO(orderDTO);

        // TODO: fix - orderModel.setCookedDishes(new ArrayList<>());
        orderModel.setCookedDishes(new ArrayList<>());
        orderModel.setCost(orderDTO.getCookingDishes().stream().mapToLong(DishDTO::getPrice).sum());
        orderModel.setStartTime(Instant.now());
        orderModel.setStatus(Status.COOKING);
        orderModel.setUser(userRepository.findByPrincipal(principal));

        log.info("Creating new Order. id={}; dishes to cook ids={}; dishes names={}; status={}; user email={}",
                orderModel.getId(),
                orderModel.getCookingDishes().stream().map(DishModel::getId).collect(Collectors.toList()),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getStatus(),
                orderModel.getUser().getEmail()
        );
        orderRepository.save(orderModel);
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
