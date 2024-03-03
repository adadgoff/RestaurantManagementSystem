package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.UserDTO;
import com.RestaurantManagementSystem.mappers.UserMapper;
import com.RestaurantManagementSystem.models.UserModel;
import com.RestaurantManagementSystem.models.enums.Role;
import com.RestaurantManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // Create.
    public boolean createUser(UserDTO userDTO) {
        UserModel userModel = userMapper.ToModelFromDTO(userDTO);

        String email = userModel.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;  // TODO: implement exception: User already exists.
        }

        userModel.setActive(true);
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userModel.getRoles().add(Role.ROLE_USER);

        log.info("Creating new User. email={}", email);
        userRepository.save(userModel);

        return true;
    }

    // Read.
    public UserDTO getUserByUUID(UUID uuid) {
        return userMapper.ToDTOFromModel(userRepository.getReferenceById(uuid));
    }
}
