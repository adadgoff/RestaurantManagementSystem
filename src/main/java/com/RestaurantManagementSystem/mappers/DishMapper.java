package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.models.DishModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface DishMapper {
    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

    DishDTO ToDTOFromModel(DishModel dishModel, @Context CycleAvoidingMappingContext context);

    DishModel ToModelFromDTO(DishDTO dishDTO, @Context CycleAvoidingMappingContext context);
}
