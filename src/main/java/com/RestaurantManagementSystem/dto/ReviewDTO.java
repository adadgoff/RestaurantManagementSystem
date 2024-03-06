package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.enums.Rating;
import lombok.Data;

import java.time.Instant;

@Data
public class ReviewDTO {
    private Long id;
    private Rating rating;
    private String text;
    private Instant publishTime;
}
