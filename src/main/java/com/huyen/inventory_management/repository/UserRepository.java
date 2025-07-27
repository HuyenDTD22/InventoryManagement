package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huyen.inventory_management.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
    
    Optional<User> findByUsernameAndDeletedFalse(String username);

    Optional<User> findByIdAndDeletedFalse(UUID id);

    List<User> findByDeletedFalse();
}
