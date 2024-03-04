package com.RestaurantManagementSystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private String text;
    private Instant publishTime;
}
