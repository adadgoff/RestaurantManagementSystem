package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    @EqualsAndHashCode.Exclude
    private List<DishDTO> waitingDishes;
    @EqualsAndHashCode.Exclude
    private List<DishDTO> cookingDishes;
    @EqualsAndHashCode.Exclude
    private List<DishDTO> cookedDishes;
    private Long cost;
    private Instant startTime;
    private Instant endTime;
    private Status status;
    private UserDTO user;
    private Boolean paid;  // TODO: implement in form + update database + flyway.
}
