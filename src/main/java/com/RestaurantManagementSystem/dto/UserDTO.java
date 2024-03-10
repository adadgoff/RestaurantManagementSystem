package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID uuid;
    private String email;
    private String name;
    private String password;
    private boolean active;
    @EqualsAndHashCode.Exclude
    private ImageDTO profileIcon;
    private Set<Role> roles;
    @EqualsAndHashCode.Exclude
    private List<OrderDTO> orders;
    @EqualsAndHashCode.Exclude
    private List<ReviewDTO> reviews;
}
