package com.api.measureconverter.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.measureconverter.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT u FROM UserEntity u WHERE u.deleteAt IS NULL")
    List<UserEntity> findAllActive();

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND u.deleteAt IS NULL")
    UserEntity findByIdActive(UUID id);

    UserEntity findByEmail(String email);
}
