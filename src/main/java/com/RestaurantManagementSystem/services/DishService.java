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
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    // Create.
    public void createDish(DishDTO dishDTO, List<MultipartFile> files) throws IOException {
        DishModel dishModel = dishMapper.ToModelFromDTO(dishDTO);

        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                ImageModel imageModel = ImageModel.builder().contentType(file.getContentType()).imgBinary(file.getBytes()).build();
                dishModel.addImageToDish(imageModel);
            }
        }

        log.info("Creating new Dish. id={}; name={}; price={}; image ids={}",
                dishModel.getId(),
                dishModel.getName(),
                dishModel.getPrice(),
                dishModel.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()));
        dishRepository.save(dishModel);
    }

    // Create.
//    public void createDish(DishModel dish, List<MultipartFile> files) throws IOException {
//        for (MultipartFile file : files) {
//            if (file.getSize() != 0) {
//                ImageModel image = ImageModel.builder().contentType(file.getContentType()).imgBinary(file.getBytes()).build();
//                dish.addImageToDish(image);
//            }
//        }
//        log.info("Creating new Dish. id={}; name={}; price={}; image ids={}", dish.getId(), dish.getName(), dish.getPrice(), dish.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()));
//        dishRepository.save(dish);
//    }
//
//    // Read.
//    public List<DishModel> getDishes(String name) {
//        if (name != null && !name.isBlank()) {
//            return dishRepository.findAllByName(name);
//        }
//        return dishRepository.findAll();
//    }
//
//    // Read.
//    public DishModel getDishById(Long id) {
//        return dishRepository.findById(id).orElse(null);
//    }
//
//    // Update.
//    // TODO: implement update.
//
//    // Delete.
//    public void deleteDish(Long id) {
//        dishRepository.deleteById(id);
//    }
}
