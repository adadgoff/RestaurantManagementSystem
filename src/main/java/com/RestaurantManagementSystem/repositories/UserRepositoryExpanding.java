package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.UserModel;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public interface UserRepositoryExpanding {
    UserModel findByPrincipal(Principal principal);
}
