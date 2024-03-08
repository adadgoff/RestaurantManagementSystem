package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>, UserRepositoryExpanding {
    UserModel findByEmail(String email);
}
