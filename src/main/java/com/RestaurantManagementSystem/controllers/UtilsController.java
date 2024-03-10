package com.RestaurantManagementSystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UtilsController {
    @GetMapping("/info")
    public String hello() {
        return "utils/info";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "utils/access_denied";
    }
}
