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

    public List<Dish> listDishes(String name) {
        if (name != null) {
            return dishRepository.findByName(name);
        }
        return dishRepository.findAll();
    }

    public void addDish(Dish dish, List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                Image image = Image.builder().contentType(file.getContentType()).imgBinary(file.getBytes()).build();
                // Возможная логика для сжатия файла.
                dish.addImageToDish(image);
            }
        }
        log.info("Adding new Dish. id: {}; name: {}; price: {}; images: {}", dish.getId(), dish.getName(), dish.getPrice(), dish.getImages().stream().map(Image::getId).collect(Collectors.toList()));
        dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }
}
