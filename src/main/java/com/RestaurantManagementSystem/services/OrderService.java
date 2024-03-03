//package com.RestaurantManagementSystem.services;
//
//import com.RestaurantManagementSystem.models.Dish;
//import com.RestaurantManagementSystem.models.Order;
//import com.RestaurantManagementSystem.repositories.OrderRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class OrderService {
//    private final OrderRepository orderRepository;
////    private final Kitchen kitchen = new Kitchen(GLOBAL_VARIABLES.COUNT_COOKS);
//
//    // Create.
//    public void createOrder(Order order) {
//        log.info("Creating new Order. id={}; dishes to cook ids={}; dishes names={}; status={}",
//                order.getId(),
//                order.getCookingDishes().stream().map(Dish::getId).collect(Collectors.toList()),
//                order.getCookingDishes().stream().map(Dish::getName).collect(Collectors.toList()),
//                order.getStatus()
//        );
//        orderRepository.save(order);
//    }
//
//    // Read all.
//    public List<Order> getOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Order getOrderById(Long id) {
//        return orderRepository.findById(id).orElse(null);
//    }
//
//    // Update.
//    // TODO: implement update.
//
//    // Delete.
//    public void deleteOrder(Long id) {
//        orderRepository.deleteById(id);
//    }
//}
