package com.RestaurantManagementSystem.services;

import com.RestaurantManagementSystem.dto.UserDTO;
import com.RestaurantManagementSystem.mappers.CycleAvoidingMappingContext;
import com.RestaurantManagementSystem.mappers.UserMapper;
import com.RestaurantManagementSystem.models.UserModel;
import com.RestaurantManagementSystem.models.enums.Role;
import com.RestaurantManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return false;  // TODO: implement exception: Email is already busy.
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setActive(true);
        // TODO: implement profileIcon.
        userDTO.setRoles(new HashSet<>(Collections.singleton(Role.ROLE_USER)));

        UserModel userModel = UserMapper.INSTANCE.ToModelFromDTO(userDTO, new CycleAvoidingMappingContext());
        userRepository.save(userModel);
        log.info("Creating new User. email={}", userModel.getEmail());
        return true;
    }

    public UserDTO getUserByUUID(UUID uuid) {
        return UserMapper.INSTANCE.ToDTOFromModel(userRepository.getReferenceById(uuid), new CycleAvoidingMappingContext());
    }
}
