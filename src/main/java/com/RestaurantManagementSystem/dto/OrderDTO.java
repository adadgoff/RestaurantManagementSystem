package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class OrderDTO {
    private Long id;
    @EqualsAndHashCode.Exclude
    private ConcurrentLinkedQueue<DishDTO> waitingDishes;
    @EqualsAndHashCode.Exclude
    private ConcurrentLinkedQueue<DishDTO> cookingDishes;
    @EqualsAndHashCode.Exclude
    private ConcurrentLinkedQueue<DishDTO> cookedDishes;
    private Long cost;
    private Instant startTime;
    private Instant endTime;
    private Status status;
    private UserDTO user;
    private Boolean paid;  // TODO: implement in form + update database + flyway.
}
