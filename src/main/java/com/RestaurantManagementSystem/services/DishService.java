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
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    // Read all. Same methods for USER and ADMIN, but some fields are not displayed for USER.
    public List<DishDTO> getDishes(String name, Boolean onlyActive) {
        // TODO: Сделать, чтобы != name, а просто dish.name.contains(name).
        List<DishModel> dishModels;
        if (onlyActive && name != null && !name.isBlank()) {
            dishModels = dishRepository.findAllByNameAndActiveOrderByName(name, true);
        } else if (onlyActive) {
            dishModels = dishRepository.findAllByActiveOrderByName(true);
        } else if (name != null && !name.isBlank()) {
            dishModels = dishRepository.findAllByNameOrderByName(name);
        } else {
            dishModels = dishRepository.findAllByOrderByName();
        }

        return dishModels.stream()
                .map(dishModel -> DishMapper.INSTANCE.ToDTOFromModel(dishModel, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }

    // Read. Same methods for USER and ADMIN, but some fields are not displayed for USER.
    public DishDTO getDishById(Long id) {
        DishModel dishModel = dishRepository.findById(id).orElse(null);
        return DishMapper.INSTANCE.ToDTOFromModel(dishModel, new CycleAvoidingMappingContext());
    }

    // Create.
    public void createDish(DishDTO dishDTO, List<MultipartFile> files, String cookingTimeStr) throws IOException {
        dishDTO.setCookingTime(TimeUtils.FromFormatStringHoursMinutesSecondsToDuration(cookingTimeStr));
        dishDTO.setImages(new ArrayList<>());
        dishDTO.setActive(dishDTO.getActive() != null);

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

    public void updateDish(DishDTO updatedDTO, List<MultipartFile> files, String cookingTimeStr) throws IOException {
        DishDTO dishDTO = DishMapper.INSTANCE.ToDTOFromModel(dishRepository.findById(updatedDTO.getId()).orElseThrow(), new CycleAvoidingMappingContext());

        dishDTO.setName(updatedDTO.getName());
        dishDTO.setDescription(updatedDTO.getDescription());
        dishDTO.setPrice(updatedDTO.getPrice());
        dishDTO.setCookingTime(TimeUtils.FromFormatStringHoursMinutesSecondsToDuration(cookingTimeStr));
        dishDTO.setCount(updatedDTO.getCount());
        dishDTO.setWeight(updatedDTO.getWeight());
        dishDTO.setActive(updatedDTO.getActive() != null);

        if (!files.isEmpty()) {
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
        }

        DishModel dishModel = DishMapper.INSTANCE.ToModelFromDTO(dishDTO, new CycleAvoidingMappingContext());
        dishRepository.save(dishModel);
        log.info("Updating new Dish. id={}; name={}; price={}", dishModel.getId(), dishModel.getName(), dishModel.getPrice());
    }

    // Delete.
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
        log.info("Deleting Dish. id={}", id);
    }
}
