package com.api.measureconverter.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.measureconverter.error.custom.NotFound404Exception;
import com.api.measureconverter.error.custom.Unauthorized401Exception;
import com.api.measureconverter.error.custom.UserAlreadyDeletedException;
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
    PasswordEncoder passwordEncoder;

    public UserDto create(RegisterDto user, Roles role) {
        UserEntity userEntity = user.toEntity();
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
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
        if (!isUserOwner(id)) {
            throw new Unauthorized401Exception("You are not the owner of this user");
        }

        UserEntity user = userRepository.findById(id).orElseThrow();
        if (user.getDeleteAt() != null) {
            throw new UserAlreadyDeletedException("User already deleted");
        }

        user.setDeleteAt(LocalDateTime.now());
        return userRepository.save(user).toDto();
    }

    private boolean isUserOwner(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        String idEmail = userRepository.findById(id)
                .orElseThrow(() -> new NotFound404Exception("User " + email + " not found"))
                .getEmail();

        return email.equals(idEmail);
    }

}
