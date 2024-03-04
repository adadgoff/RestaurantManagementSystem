package com.RestaurantManagementSystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ImageDTO {
    private Long id;
    private String contentType;
    private byte[] imgBinary;
}
