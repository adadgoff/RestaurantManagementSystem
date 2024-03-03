package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.models.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn
@Mapper(componentModel = "spring")
public interface DishMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "cookingTime", target = "cookingTime")
    @Mapping(source = "count", target = "count")
    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "images", target = "images")
    @Mapping(source = "reviews", target = "reviews")
    DishDTO ToDTOFromModel(DishModel dishModel);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "cookingTime", target = "cookingTime")
    @Mapping(source = "count", target = "count")
    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "images", target = "images")
    @Mapping(source = "reviews", target = "reviews")
    DishModel ToModelFromDTO(DishDTO dishDTO);
}
