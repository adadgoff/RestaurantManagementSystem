package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.UserDTO;
import com.RestaurantManagementSystem.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "profileIcon", target = "profileIcon")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "orders", target = "orders")
    @Mapping(source = "reviews", target = "reviews")
    UserDTO ToDTOFromModel(UserModel userModel);

    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "profileIcon", target = "profileIcon")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "orders", target = "orders")
    @Mapping(source = "reviews", target = "reviews")
    UserModel ToModelFromDTO(UserDTO userDTO);
}
