package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.exceptions.orderExceptions.CreateOrderException;
import com.RestaurantManagementSystem.exceptions.orderExceptions.GetOrderException;
import com.RestaurantManagementSystem.exceptions.orderExceptions.UpdateOrderException;
import com.RestaurantManagementSystem.exceptions.userExceptions.InvalidUserException;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.DishMapper;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.mappers.UserMapper;
import com.RestaurantManagementSystem.models.DishModel;
import com.RestaurantManagementSystem.models.OrderModel;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.DishRepository;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import com.RestaurantManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    //    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COUNT_COOKS);

    public OrderDTO getOrderById(Long id, Principal principal) throws GetOrderException {
        OrderDTO orderDTO = OrderMapper.INSTANCE.ToDTOFromModel(orderRepository.findById(id).orElseThrow(), new CycleAvoidingMappingContext());
        if (!Objects.equals(orderDTO.getUser().getEmail(), userRepository.findByPrincipal(principal).getEmail())) {
            throw new GetOrderException("Доступ запрещен.");
        }
        return orderDTO;
    }

    public List<OrderDTO> getOrders(Principal principal) {
        return orderRepository.findAllByUserEmailOrderByStartTime(userRepository.findByPrincipal(principal).getEmail())
                .stream().map(orderModel -> OrderMapper.INSTANCE.ToDTOFromModel(orderModel, new CycleAvoidingMappingContext()))
                .toList();
    }

    public void createOrder(Map<Long, Long> dishCounts, Principal principal) {
        OrderDTO orderDTO = new OrderDTO();

        List<DishDTO> cookingDishes = new ArrayList<>();
        for (Long dishId : dishCounts.keySet()) {
            DishDTO cookingDish = DishMapper.INSTANCE.ToDTOFromModel(dishRepository.findById(dishId).orElseThrow(), new CycleAvoidingMappingContext());
            long count = dishCounts.get(dishId);
            if (count > cookingDish.getCount()) {
                throw new CreateOrderException("Запрещено заказывать больше блюд, чем их оставшееся количество в ресторане.");
            }
            for (long i = 0; i < count; i++) {
                cookingDishes.add(cookingDish);
            }
            cookingDish.setCount(cookingDish.getCount() - count);
            dishRepository.save(DishMapper.INSTANCE.ToModelFromDTO(cookingDish, new CycleAvoidingMappingContext()));
        }

        orderDTO.setCookingDishes(cookingDishes);
        orderDTO.setCookedDishes(new ArrayList<>());
        orderDTO.setCost(cookingDishes.stream().mapToLong(DishDTO::getPrice).sum());
        orderDTO.setStartTime(Instant.now());
        orderDTO.setStatus(Status.COOKING);
        orderDTO.setUser(UserMapper.INSTANCE.ToDTOFromModel(userRepository.findByPrincipal(principal), new CycleAvoidingMappingContext()));

        // TODO: закинуть заказ на кухню + обновить БД.

        OrderModel orderModel = orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));
        log.info("Creating new Order. id={}; dishes to cook ids={}; dishes names={}; status={}; user email={}",
                orderModel.getId(),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getStatus(),
                orderModel.getUser().getEmail()
        );
    }

    public void updateOder(OrderDTO orderDTO, Map<Long, Long> dishCounts, Principal principal) {
        // User verification.
        if (!Objects.equals(principal.getName(), orderDTO.getUser().getEmail())) {
            throw new InvalidUserException("Доступ запрещен.");
        }

        // Status order verification.
        if (orderDTO.getStatus() != Status.COOKING) {
            throw new UpdateOrderException("Запрещено добавлять блюдо в заказ с отличным от COOKING статусом.");
        }

        List<DishDTO> addedCookingDishes = orderDTO.getCookingDishes();
        for (Long dishId : dishCounts.keySet()) {
            DishDTO addedCookingDish = DishMapper.INSTANCE.ToDTOFromModel(dishRepository.findById(dishId).orElseThrow(), new CycleAvoidingMappingContext());
            long count = dishCounts.get(dishId);
            if (count > addedCookingDish.getCount()) {
                throw new CreateOrderException("Запрещено заказывать больше блюд, чем их оставшееся количество в ресторане.");
            }
            for (long i = 0; i < count; i++) {
                addedCookingDishes.add(addedCookingDish);
            }
            addedCookingDish.setCount(addedCookingDish.getCount() - count);
            dishRepository.save(DishMapper.INSTANCE.ToModelFromDTO(addedCookingDish, new CycleAvoidingMappingContext()));
        }

        OrderModel orderModel = orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));
        log.info("Updating Order. id={}; dishes to cook ids={}; dishes names={}; status={}; user email={}",
                orderModel.getId(),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getCookingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getStatus(),
                orderModel.getUser().getEmail()
        );
    }
}
