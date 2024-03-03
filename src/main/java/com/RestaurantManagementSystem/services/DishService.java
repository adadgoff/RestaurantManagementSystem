package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.mappers.DishMapper;
import com.RestaurantManagementSystem.models.DishModel;
import com.RestaurantManagementSystem.models.ImageModel;
import com.RestaurantManagementSystem.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    // Read all.
    public List<DishModel> getDishes(String name) {
        if (name != null && !name.isBlank()) {
            return dishRepository.findAllByName(name);
        }
        return dishRepository.findAll();
    }

    // Create.
    public void createDish(DishDTO dishDTO, List<MultipartFile> files) throws IOException {
        DishModel dishModel = dishMapper.ToModelFromDTO(dishDTO);

        dishModel.setImages(new ArrayList<>());
        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                ImageModel imageModel = ImageModel.builder()
                        .contentType(file.getContentType())
                        .imgBinary(file.getBytes()).build();
                dishModel.addImageToDish(imageModel);
            }
        }

        log.info("Creating new Dish. id={}; name={}; price={}; image ids={}",
                dishModel.getId(),
                dishModel.getName(),
                dishModel.getPrice(),
                dishModel.getImages() != null ? dishModel.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()) : null);
        dishRepository.save(dishModel);
    }

    // Read.
    public DishDTO getDishById(Long id) {
        return dishMapper.ToDTOFromModel(dishRepository.findById(id).orElse(null));
    }

//    // Update.
//    // TODO: implement update.

    // Delete.
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
