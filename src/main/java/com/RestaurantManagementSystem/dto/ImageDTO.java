package com.RestaurantManagementSystem.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageDTO {
    private Long id;
    private String contentType;
    private byte[] imgBinary;
    private DishDTO dish;
    private UserDTO user;
}
