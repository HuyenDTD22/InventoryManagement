package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID>{
    boolean existsByCode(String code);

    Optional<Material> findByIdAndDeletedFalse(UUID id);

    List<Material> findByDeletedFalse();
}
