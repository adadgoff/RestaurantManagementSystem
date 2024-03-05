package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.ImageDTO;
import com.RestaurantManagementSystem.models.ImageModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDTO ToDTOFromModel(ImageModel imageModel, @Context CycleAvoidingMappingContext context);

    ImageModel ToModelFromDTO(ImageDTO imageDTO, @Context CycleAvoidingMappingContext context);
}
