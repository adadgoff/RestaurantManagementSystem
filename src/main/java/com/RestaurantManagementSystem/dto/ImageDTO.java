package com.RestaurantManagementSystem.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
public class ImageDTO {
    private Long id;
    private String contentType;
    private byte[] imgBinary;
    @EqualsAndHashCode.Exclude
    private DishDTO dish;
    @EqualsAndHashCode.Exclude
    private UserDTO user;
}
