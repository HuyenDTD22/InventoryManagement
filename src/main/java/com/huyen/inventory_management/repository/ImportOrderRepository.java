package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.ImportOrder;

@Repository
public interface ImportOrderRepository extends JpaRepository<ImportOrder, UUID>{
    List<ImportOrder> findByDeletedFalse();

    Optional<ImportOrder> findByIdAndDeletedFalse(UUID id);
}
