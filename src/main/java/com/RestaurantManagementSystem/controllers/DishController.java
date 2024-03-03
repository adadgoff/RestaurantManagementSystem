package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.GLOBAL_VARIABLES;
import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.services.DishService;
import com.RestaurantManagementSystem.utils.TimeUtils;
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

    // TODO: dish/admin/CRUD (all/id).
    // TODO: dish/menu/R (all/id).

    // Read all.
    @GetMapping("/dish/all")
    public String dishes(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("dishes", dishService.getDishes(name));
        model.addAttribute("regexTime", GLOBAL_VARIABLES.REGEX_TIME);
        return "dishes";
    }

    // Create.
    @PostMapping("/dish/create")
    public String createDish(@RequestParam("files") List<MultipartFile> files, String cookingTimeStr, DishDTO dishDTO) throws IOException {
        dishDTO.setCookingTime(TimeUtils.FromFormatStringHoursMinutesSecondsToDuration(cookingTimeStr));
        dishService.createDish(dishDTO, files);
        return "redirect:/dish/all";
    }

    // Read.
    @GetMapping("/dish/{id}")
    public String getDish(@PathVariable Long id, Model model) {
        DishDTO dishDTO = dishService.getDishById(id);
        model.addAttribute("dish", dishDTO);
        return "dish";
    }

    // Update.
    // TODO: implement update controller.

    // Delete.
    @PostMapping("/dish/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/dish/all";
    }
}
