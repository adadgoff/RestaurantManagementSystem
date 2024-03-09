package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
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
import com.RestaurantManagementSystem.utils.OrderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    private final OrderUtils orderUtils;

    @Autowired
    private final KitchenService kitchenService = new KitchenService(GLOBAL_VARIABLES.COUNT_COOKS);

    public OrderDTO getOrderById(Long id) {
        return OrderMapper.INSTANCE.ToDTOFromModel(
                orderRepository.findById(id).orElseThrow(), new CycleAvoidingMappingContext()
        );
    }

    public List<OrderDTO> getOrders(Principal principal) {
        return orderRepository.findAllByUserEmailOrderByStartTime(userRepository.findByPrincipal(principal).getEmail())
                .stream().map(orderModel -> OrderMapper.INSTANCE.ToDTOFromModel(orderModel, new CycleAvoidingMappingContext()))
                .toList();
    }

    public void createOrder(Map<Long, Long> dishCounts, Principal principal) throws InterruptedException {
        if (!orderUtils.isValidCounts(dishCounts)) {
            return;
        }

        OrderDTO orderDTO = new OrderDTO();
        ConcurrentLinkedQueue<DishDTO> waitingDishes = new ConcurrentLinkedQueue<>();
        for (Long dishId : dishCounts.keySet()) {
            DishDTO waitingDish = DishMapper.INSTANCE.ToDTOFromModel(
                    dishRepository.findById(dishId).orElseThrow(), new CycleAvoidingMappingContext());
            long count = dishCounts.get(dishId);
            for (long i = 0; i < count; i++) {
                waitingDishes.add(waitingDish);
            }
            waitingDish.setCount(waitingDish.getCount() - count);
            dishRepository.save(DishMapper.INSTANCE.ToModelFromDTO(waitingDish, new CycleAvoidingMappingContext()));
        }

        orderDTO.setWaitingDishes(waitingDishes);
        orderDTO.setCookingDishes(new ConcurrentLinkedQueue<>());
        orderDTO.setCookedDishes(new ConcurrentLinkedQueue<>());
        orderDTO.setCost(waitingDishes.stream().mapToLong(DishDTO::getPrice).sum());
        orderDTO.setStartTime(Instant.now());
        orderDTO.setStatus(Status.COOKING);
        orderDTO.setUser(UserMapper.INSTANCE.ToDTOFromModel(userRepository.findByPrincipal(principal), new CycleAvoidingMappingContext()));
        orderDTO.setPaid(false);

        OrderModel orderModel = orderRepository.save(
                OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));

        kitchenService.addOrder(OrderMapper.INSTANCE.ToDTOFromModel(orderModel, new CycleAvoidingMappingContext()));

        log.info("Creating new Order. id={}; dishes names={}; status={}; user email={}",
                orderModel.getId(),
                orderModel.getWaitingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getStatus(),
                orderModel.getUser().getEmail()
        );
    }

    public void updateOder(OrderDTO orderDTO, Map<Long, Long> dishCounts) {
        if (!orderUtils.isCookingStatus(orderDTO) || !orderUtils.isValidCounts(dishCounts)) {
            return;
        }

        long addedCost = 0;
        for (Long dishId : dishCounts.keySet()) {
            DishDTO addedWaitingDish = DishMapper.INSTANCE.ToDTOFromModel(dishRepository.findById(dishId).orElseThrow(), new CycleAvoidingMappingContext());
            long count = dishCounts.get(dishId);
            for (long i = 0; i < count; i++) {
                orderDTO.getWaitingDishes().add(addedWaitingDish);
            }
            addedCost += addedWaitingDish.getPrice() * count;
            addedWaitingDish.setCount(addedWaitingDish.getCount() - count);
            dishRepository.save(DishMapper.INSTANCE.ToModelFromDTO(addedWaitingDish, new CycleAvoidingMappingContext()));
        }

        orderDTO.setWaitingDishes(orderDTO.getWaitingDishes());
        orderDTO.setCost(orderDTO.getCost() + addedCost);

        OrderModel orderModel = orderRepository.save(OrderMapper.INSTANCE.ToModelFromDTO(orderDTO, new CycleAvoidingMappingContext()));
        log.info("Updating Order. id={}; new dishes names={}; status={}; user email={}",
                orderModel.getId(),
                orderModel.getWaitingDishes().stream().map(DishModel::getName).collect(Collectors.toList()),
                orderModel.getStatus(),
                orderModel.getUser().getEmail()
        );
    }

    public void cancelOrder(OrderDTO orderDTO) {  // TODO: only own user can cancel.
        if (!orderUtils.isCookingStatus(orderDTO)) {
            return;
        }
    }
}
