package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.dto.UserDTO;
import com.RestaurantManagementSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(UserDTO userDTO, Model model) {
        if (!userService.createUser(userDTO)) {
            // TODO: show error in html.
            model.addAttribute("errorMessage", "Пользователь с email="
                    + userDTO.getEmail() + " уже существует.");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}
