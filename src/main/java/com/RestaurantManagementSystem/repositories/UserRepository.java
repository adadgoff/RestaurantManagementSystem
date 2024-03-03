package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByEmail(String email);
}
