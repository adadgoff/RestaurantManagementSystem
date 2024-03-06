package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController("")
public class DishController {
    private final DishService dishService;

    @GetMapping("/dish/{id}")
    public String getDish(@PathVariable Long id, Model model) {
        DishDTO dishDTO = dishService.getDishById(id);
        model.addAttribute("dish", dishDTO);
        return "dish";
    }

    @GetMapping("/dish/menu")
    public String getActualDishes(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("dishes", dishService.getDishes(name));
        model.addAttribute("regexTime", GLOBAL_VARIABLES.REGEX_TIME);
        return "dishes";
    }
}
