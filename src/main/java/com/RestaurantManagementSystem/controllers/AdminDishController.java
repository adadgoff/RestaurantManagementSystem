package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminDishController {
    private final DishService dishService;

    @GetMapping("/admin/dish/panel")
    public String getAdminDishes(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("dishes", dishService.getDishes(name, false));
        model.addAttribute("regexTime", GLOBAL_VARIABLES.REGEX_TIME);
        return "admin/dish_panel";
    }

    @GetMapping("/admin/dish/{id}")
    public String getAdminDishById(@PathVariable Long id, Model model) {
        model.addAttribute("dish", dishService.getDishById(id));
        model.addAttribute("regexTime", GLOBAL_VARIABLES.REGEX_TIME);
        return "admin/dish";
    }

    @PostMapping("/admin/dish/create")
    public String createDish(DishDTO dishDTO, @RequestParam("files") List<MultipartFile> files, String cookingTimeStr) throws IOException {
        dishService.createDish(dishDTO, files, cookingTimeStr);
        return "redirect:/admin/dish/panel";
    }

    @PostMapping("/admin/dish/update")
//    @PatchMapping("/admin/dish/update/{id}")  // не использовал, так как html формы не имеют method="patch".
    public String updateDish(DishDTO updatedDTO, @RequestParam("files") List<MultipartFile> files, String cookingTimeStr) throws IOException {
        dishService.updateDish(updatedDTO, files, cookingTimeStr);
        return "redirect:/admin/dish/{id}";
    }

    @PostMapping("/admin/dish/delete/{id}")  // TODO: изменить на более безопасную версию.
//    @DeleteMapping("/admin/dish/delete/{id}")  // не использовал, так как html формы не имеют method="delete".
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/admin/dish/panel";
    }
}