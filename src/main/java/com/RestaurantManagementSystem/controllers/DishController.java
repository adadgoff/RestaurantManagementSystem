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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/")
    public String dishes(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("dishes", dishService.listDishes(name));
        return "dishes";
    }

    // Create.
    @PostMapping("/dish/create")
    public String createDish(@RequestParam("files") List<MultipartFile> files, Dish dish) throws IOException {
        dishService.createDish(dish, files);
        return "redirect:/";
    }

    // Read.
    @GetMapping("/dish/{id}")
    public String getDish(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDishById(id);
        model.addAttribute("dish", dish);
        return "dish-info";
    }

    // Update.
    // TODO: implement update controller.

    // Delete.
    @PostMapping("/dish/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/";
    }
}
