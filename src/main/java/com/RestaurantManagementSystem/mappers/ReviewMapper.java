package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.ReviewDTO;
import com.RestaurantManagementSystem.models.ReviewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDTO ToDTOFromModel(ReviewModel reviewModel);

    ReviewModel ToModelFromDTO(ReviewDTO reviewDTO);
}
