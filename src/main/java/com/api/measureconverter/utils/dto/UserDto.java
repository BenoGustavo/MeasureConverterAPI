package com.api.measureconverter.utils.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.api.measureconverter.model.UserEntity;
import com.api.measureconverter.utils.enums.Roles;

import lombok.Data;

@Data
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private Roles role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deleteAt;
    private LocalDateTime lastLogin;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.id);
        userEntity.setUsername(this.username);
        userEntity.setEmail(this.email);
        userEntity.setRole(this.role);
        userEntity.setCreatedAt(this.createdAt);
        userEntity.setUpdatedAt(this.updatedAt);
        userEntity.setDeleteAt(this.deleteAt);
        userEntity.setLastLogin(this.lastLogin);
        return userEntity;
    }
}
