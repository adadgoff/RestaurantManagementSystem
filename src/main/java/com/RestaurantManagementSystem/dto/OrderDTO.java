package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private List<DishDTO> cookingDishes;
    private List<DishDTO> cookedDishes;
    private Long cost;
    private Instant startTime;
    private Instant endTime;
    private Status status;
}
