package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.DishDTO;
import com.RestaurantManagementSystem.dto.ImageDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.DishMapper;
import com.RestaurantManagementSystem.models.DishModel;
import com.RestaurantManagementSystem.repositories.DishRepository;
import com.RestaurantManagementSystem.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    // Read all.
    public List<DishModel> getDishes(String name) {
        if (name != null && !name.isBlank()) {
            return dishRepository.findAllByName(name);
        }
        return dishRepository.findAll();
    }

    // Create.
    public void createDish(DishDTO dishDTO, List<MultipartFile> files, String cookingTimeStr) throws IOException {
        dishDTO.setCookingTime(TimeUtils.FromFormatStringHoursMinutesSecondsToDuration(cookingTimeStr));
        dishDTO.setActive(true);
        dishDTO.setImages(new ArrayList<>());

        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                ImageDTO imageDTO = ImageDTO.builder()
                        .contentType(file.getContentType())
                        .imgBinary(file.getBytes())
                        .dish(dishDTO)
                        .build();
                dishDTO.getImages().add(imageDTO);
            }
        }

        DishModel dishModel = DishMapper.INSTANCE.ToModelFromDTO(dishDTO, new CycleAvoidingMappingContext());
        dishRepository.save(dishModel);
        log.info("Creating new Dish. id={}; name={}; price={}", dishModel.getId(), dishModel.getName(), dishModel.getPrice());
    }

    // Read.
    public DishDTO getDishById(Long id) {
        DishModel dishModel = dishRepository.findById(id).orElse(null);
        return DishMapper.INSTANCE.ToDTOFromModel(dishModel, new CycleAvoidingMappingContext());
    }

//    // Update.
//    // TODO: implement update.

    // Delete.
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
