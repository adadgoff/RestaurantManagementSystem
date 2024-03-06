package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
//@RestController() // TODO: write prefix.
public class DishController {
    private final DishService dishService;

    @GetMapping("/dish/menu")
    public String getUserDishes(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("dishes", dishService.getDishes(name, true));
        return "user/dish_menu";
    }

    @GetMapping("/dish/{id}")
    public String getUserDish(@PathVariable Long id, Model model) {
        DishDTO dishDTO = dishService.getDishById(id);
        model.addAttribute("dish", dishDTO);
        return "user/dish";
    }
}
