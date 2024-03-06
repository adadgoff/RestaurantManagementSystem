package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController("/admin")
public class AdminDishController {
    private final DishService dishService;

    @GetMapping("/dish/panel")
    public String getAllDishes(Model model) {
        model.addAttribute("dishes", dishService.getDishes(null));
        return "admin/dish_panel";
    }

    @PostMapping("/dish/create")
    public String createDish(@RequestParam("files") List<MultipartFile> files, String cookingTimeStr, DishDTO dishDTO) throws IOException {
        dishService.createDish(dishDTO, files, cookingTimeStr);
        return "redirect:/dish/all";
    }
}
