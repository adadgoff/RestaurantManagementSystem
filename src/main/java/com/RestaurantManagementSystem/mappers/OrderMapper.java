package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.models.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO ToDTOFromModel(OrderModel orderModel);

    OrderModel ToModelFromDTO(OrderDTO orderDTO);
}
