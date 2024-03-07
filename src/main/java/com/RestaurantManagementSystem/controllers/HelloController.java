package com.RestaurantManagementSystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
