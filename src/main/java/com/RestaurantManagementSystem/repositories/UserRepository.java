package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.UserModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>, UserRepositoryExpanding {
    UserModel findByEmail(String email);
}
