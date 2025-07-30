package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID>{
    
    boolean existsByNameAndDeletedFalse(String name);

    Optional<Supplier> findByIdAndDeletedFalse(UUID id);

    List<Supplier> findByDeletedFalse();
}
