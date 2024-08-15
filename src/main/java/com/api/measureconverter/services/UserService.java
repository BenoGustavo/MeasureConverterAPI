package com.api.measureconverter.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.measureconverter.model.UserEntity;
import com.api.measureconverter.repositories.UserRepository;
import com.api.measureconverter.utils.dto.RegisterDto;
import com.api.measureconverter.utils.dto.UserDto;
import com.api.measureconverter.utils.enums.Roles;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto create(RegisterDto user, Roles role) {
        UserEntity userEntity = user.toEntity();
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity).toDto();
    }

    // Get active users
    public UserDto getActiveById(UUID id) {
        return userRepository.findByIdActive(id).toDto();
    }

    public List<UserDto> getActiveOnly() {
        return userRepository.findAllActive().stream().map(UserEntity::toDto).toList();
    }

    // Get all
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserEntity::toDto).toList();
    }

    public UserDto delete(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        if (user.getDeleteAt() != null) {
            throw new RuntimeException("User already deleted");
        }

        user.setDeleteAt(LocalDateTime.now());
        return userRepository.save(user).toDto();
    }
}
