package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.Image;
import com.RestaurantManagementSystem.models.Order;
import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
public class Dish {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Duration cookingTime;
    private Long count;
    private Integer weight;
    private List<Image> images;
    private Order order;

    public static Dish toDTO(com.RestaurantManagementSystem.models.Dish dish) {

    }
}
