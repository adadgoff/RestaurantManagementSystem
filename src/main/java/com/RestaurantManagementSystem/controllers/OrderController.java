package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.models.Order;
import com.RestaurantManagementSystem.services.DishService;
import com.RestaurantManagementSystem.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final DishService dishService;

    // Read all.
    @GetMapping("/order/all")
    public String orders(Model model) {
        // TODO: restrict access.
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("regexIds", GLOBAL_VARIABLES.REGEX_IDS);
        return "orders";
    }

    // Create.
    @PostMapping("order/create")
    public String createOrder(String orderDishIdsStr, Order order) {
        // TODO: need to speed up. very slow.
        List<Long> dishIds = Arrays.stream(orderDishIdsStr.split("\\s+")).map(Long::parseLong).toList();
        List<Dish> cookingDishes = dishIds.stream().map(dishService::getDishById).toList();

        order.setCookingDishes(cookingDishes);
        orderService.createOrder(order);
        return "redirect:order/all";
    }
}
