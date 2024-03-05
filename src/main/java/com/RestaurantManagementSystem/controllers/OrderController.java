package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.services.DishService;
import com.RestaurantManagementSystem.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j  // TODO: dont forget to delete.
@RequiredArgsConstructor
public class OrderController {
    private final DishService dishService;
    private final OrderService orderService;

    // Read all.
    @GetMapping("/order/all")
    public String orders(Model model) {
        // TODO: restrict access.
        model.addAttribute("orders", orderService.getOrders());
        return "orders";
    }

    // Create.
    @PostMapping("/order/create")
    public String createOrder(Long idDishToCook, OrderDTO orderDTO, Principal principal) {
        orderService.createOrder(idDishToCook, orderDTO, principal);
        return "redirect:/order/all";
    }
}
