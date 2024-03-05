package com.RestaurantManagementSystem.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewDTO {
    private Long id;
    private String text;
    private Instant publishTime;
}
