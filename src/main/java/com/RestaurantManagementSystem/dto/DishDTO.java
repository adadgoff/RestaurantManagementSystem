package com.RestaurantManagementSystem.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.util.List;

@Data
public class DishDTO {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Duration cookingTime;
    private Long count;
    private Integer weight;
    private Boolean active;
    @EqualsAndHashCode.Exclude
    private List<ImageDTO> images;
    @EqualsAndHashCode.Exclude
    private List<ReviewDTO> reviews;
}
