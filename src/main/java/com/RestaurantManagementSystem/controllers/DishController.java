package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/")
    public String dishes(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("dishes", dishService.listDishes(name));
        return "dishes";
    }

    @GetMapping("/dish/{id}")
    public String dishInfo(@PathVariable Long id, Model model) {
        model.addAttribute("dish", dishService.getDishById(id));
        return "dish-info";
    }

    @PostMapping("/dish/create")
    public String createDish(Dish dish) {
        dishService.addDish(dish);
        return "redirect:/";
    }

    @PostMapping("/dish/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/";
    }
}
