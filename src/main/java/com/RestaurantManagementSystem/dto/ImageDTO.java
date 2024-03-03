package com.RestaurantManagementSystem.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String contentType;
    private byte[] imgBinary;
}
