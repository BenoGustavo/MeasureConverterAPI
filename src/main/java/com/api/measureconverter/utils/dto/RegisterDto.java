package com.api.measureconverter.utils.dto;

import com.api.measureconverter.model.UserEntity;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterDto(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.username);
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        return userEntity;
    }
}
