package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.models.Dish;
import com.RestaurantManagementSystem.models.Image;
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

    // Create.
    public void createDish(Dish dish, List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                Image image = Image.builder().contentType(file.getContentType()).imgBinary(file.getBytes()).build();
                // TODO: compress image and take only image.
                dish.addImageToDish(image);
            }
        }
        log.info("Creating new Dish. id={}; name={}; price={}; image ids={}", dish.getId(), dish.getName(), dish.getPrice(), dish.getImages().stream().map(Image::getId).collect(Collectors.toList()));
        dishRepository.save(dish);
    }

    // Read.
    public List<Dish> getDishes(String name) {
        if (name != null && !name.isBlank()) {
            return dishRepository.findAllByName(name);
        }
        return dishRepository.findAll();
    }

    // Read.
    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    // Update.
    // TODO: implement update controller.

    // Delete.
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
