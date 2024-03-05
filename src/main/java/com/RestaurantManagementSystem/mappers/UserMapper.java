package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.UserDTO;
import com.RestaurantManagementSystem.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO ToDTOFromModel(UserModel userModel);

    UserModel ToModelFromDTO(UserDTO userDTO);
}
