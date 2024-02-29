package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// TODO: maybe bug with UUID?
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
