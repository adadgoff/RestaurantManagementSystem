package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.models.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn
@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "cookingDishes", target = "cookingDishes")
    @Mapping(source = "cookedDishes", target = "cookedDishes")
    @Mapping(source = "cost", target = "cost")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    @Mapping(source = "status", target = "status")
    OrderDTO ToDTOFromModel(OrderModel orderModel);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "cookingDishes", target = "cookingDishes")
    @Mapping(source = "cookedDishes", target = "cookedDishes")
    @Mapping(source = "cost", target = "cost")
    @Mapping(source = "startTime", target = "startTime")
    @Mapping(source = "endTime", target = "endTime")
    @Mapping(source = "status", target = "status")
    OrderModel ToModelFromDTO(OrderDTO orderDTO);
}
