//package com.RestaurantManagementSystem.controllers;
//
//import com.RestaurantManagementSystem.models.Order;
//import com.RestaurantManagementSystem.services.DishService;
//import com.RestaurantManagementSystem.services.OrderService;
//import com.RestaurantManagementSystem.services.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.UUID;
//
//@Controller
//@Slf4j  // TODO: dont forget to delete.
//@RequiredArgsConstructor
//public class OrderController {
//    private final OrderService orderService;
//    private final DishService dishService;
//    private final UserService userService;
//
//    // Read all.
//    @GetMapping("/order/all")
//    public String orders(Model model) {
//        // TODO: restrict access.
//        model.addAttribute("orders", orderService.getOrders());
//        return "orders";
//    }
//
//    // Create.
//    @PostMapping("/order/create")
//    public String createOrder(Long idDishToCook, Order order) {
//        order.getCookingDishes().add(dishService.getDishById(idDishToCook));
//        order.setUser(userService.getUserByUUID(UUID.fromString("2a88b56d-b87f-4997-8e13-8465e0ffb8e7")));  // TODO: real user.
//        orderService.createOrder(order);
//        return "redirect:/order/all";
//    }
//}
