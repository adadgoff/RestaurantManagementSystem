package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.models.User;
import com.RestaurantManagementSystem.models.enums.Role;
import com.RestaurantManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Create.
    public boolean createUser(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;  // User already exists.
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);

        log.info("Creating new User. email={}", email);
        userRepository.save(user);

        return true;
    }

    // Read.
    public User getUserByUUID(UUID uuid) {
        return userRepository.getReferenceById(uuid);
    }
}
