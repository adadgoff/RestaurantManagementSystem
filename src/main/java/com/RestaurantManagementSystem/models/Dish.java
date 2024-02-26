package com.RestaurantManagementSystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    private Long Id;
    private String Name;
    private Long Price;
//    private List<Long> ImageIds;
//    private List<Long> ReviewIds;
}
