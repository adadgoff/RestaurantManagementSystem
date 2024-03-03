package com.RestaurantManagementSystem.dto;

import com.RestaurantManagementSystem.models.enums.Role;
import lombok.Data;
import org.apache.catalina.User;

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
    private ImageDTO profileIcon;
    private Set<Role> roles;
    private List<OrderDTO> orders;
    private List<ReviewDTO> reviews;
}
