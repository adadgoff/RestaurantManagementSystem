package com.RestaurantManagementSystem.mappers;

import com.RestaurantManagementSystem.dto.ImageDTO;
import com.RestaurantManagementSystem.models.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "contentType", target = "contentType")
    @Mapping(source = "imgBinary", target = "imgBinary")
    ImageDTO ToDTOFromModel(ImageModel imageModel);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "contentType", target = "contentType")
    @Mapping(source = "imgBinary", target = "imgBinary")
    ImageModel ToModelFromDTO(ImageDTO imageDTO);
}
