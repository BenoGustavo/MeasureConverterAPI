package com.api.measureconverter.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.measureconverter.model.UserEntity;
import com.api.measureconverter.repositories.UserRepository;
import com.api.measureconverter.utils.dto.UserDto;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto create(UserEntity user) {
        return userRepository.save(user).toDto();
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
