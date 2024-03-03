package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.ReviewDTO;
import com.RestaurantManagementSystem.models.ReviewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn
@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "publishTime", target = "publishTime")
    ReviewDTO ToDTOFromModel(ReviewModel reviewModel);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "publishTime", target = "publishTime")
    ReviewModel ToModelFromDTO(ReviewDTO reviewDTO);
}
