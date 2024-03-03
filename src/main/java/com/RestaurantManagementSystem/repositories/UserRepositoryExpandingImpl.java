package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class UserRepositoryExpandingImpl implements UserRepositoryExpanding {
    private final UserRepository userRepository;

    @Lazy
    public UserRepositoryExpandingImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel findByPrincipal(Principal principal) {
        if (principal == null) {
            return new UserModel();
        }
        return userRepository.findByEmail(principal.getName());
    }
}
