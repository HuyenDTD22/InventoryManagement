package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {

    boolean existsByNameAndDeletedFalse(String name);

    Optional<Warehouse> findByIdAndDeletedFalse(UUID id);

    List<Warehouse> findByDeletedFalse();
}
