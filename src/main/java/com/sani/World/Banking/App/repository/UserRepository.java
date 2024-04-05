package com.sani.World.Banking.App.repository;

import com.sani.World.Banking.App.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);

    Boolean existsByAccountNumber(String accountNumber);

    UserEntity findByAccountNumber(String AccountNumber);

    Optional<UserEntity> findByEmail(String email);
}
