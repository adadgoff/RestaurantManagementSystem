package com.RestaurantManagementSystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;

@Data
@Getter
@Setter
public class DishDTO {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Duration cookingTime;
    private Long count;
    private Integer weight;
    private List<ImageDTO> images;
    private List<ReviewDTO> reviews;
}
