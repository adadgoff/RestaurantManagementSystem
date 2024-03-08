package com.RestaurantManagementSystem.utils;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.DishMapper;
import com.RestaurantManagementSystem.mappers.OrderMapper;
import com.RestaurantManagementSystem.mappers.UserMapper;
import com.RestaurantManagementSystem.models.OrderModel;
import com.RestaurantManagementSystem.models.enums.Status;
import com.RestaurantManagementSystem.repositories.DishRepository;
import com.RestaurantManagementSystem.repositories.OrderRepository;
import com.RestaurantManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderUtils {
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public boolean hasPrincipalRights(OrderDTO orderDTO, Principal principal) {
        return UserMapper.INSTANCE.ToDTOFromModel(
                userRepository.findByPrincipal(principal), new CycleAvoidingMappingContext()
        ).getOrders().contains(orderDTO);
    }

    public boolean isCookingStatus(OrderDTO orderDTO) {
        return orderDTO.getStatus() == Status.COOKING;
    }

    public boolean isValidCounts(Map<Long, Long> dishCounts) {
        for (Long dishId : dishCounts.keySet()) {
            DishDTO cookingDish = DishMapper.INSTANCE.ToDTOFromModel(dishRepository.findById(dishId).orElseThrow(), new CycleAvoidingMappingContext());
            long orderDishCount = dishCounts.get(dishId);
            if (orderDishCount > cookingDish.getCount()) {
                return false;
            }
        }
        return true;
    }
}
