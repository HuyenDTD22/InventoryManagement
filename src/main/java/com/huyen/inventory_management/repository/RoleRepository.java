package com.huyen.inventory_management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huyen.inventory_management.model.Role;


public interface RoleRepository extends JpaRepository<Role, UUID>{
    Optional<Role> findByName(String name);
}
