package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

}
