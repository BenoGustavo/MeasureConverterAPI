package com.api.measureconverter.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.api.measureconverter.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT u FROM UserEntity u WHERE u.deleteAt IS NULL")
    List<UserEntity> findAllActive();

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND u.deleteAt IS NULL")
    UserEntity findByIdActive(UUID id);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmailAndUsername(String email, String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.lastLogin = CURRENT_TIMESTAMP WHERE u.id = :id")
    void updateLastLogin(@Param("id") UUID id);
}
