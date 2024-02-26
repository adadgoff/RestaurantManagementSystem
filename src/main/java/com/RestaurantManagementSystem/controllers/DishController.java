package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/")
    public String dishes(Model model) {
        model.addAttribute("dishes", dishService.getDishes());
        return "dishes";
    }

//    @GetMapping("/dish/{id}")
//    public String dishInfo(@PathVariable Long id, Model model) {
//        model.addAttribute("dish", dishService.getById(id));
//        return "dish-info";
//    }
//
//    @PostMapping("/dish/create")
//    public String createDish(Dish dish) {
//        dishService.addDish(dish);
//        return "redirect:/";
//    }
//
//    @PostMapping("/product/delete/{id}")
//    public String deleteDish(@PathVariable Long id) {
//        dishService.deleteDish(id);
//        return "redirect:/";
//    }
}
