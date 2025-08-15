package com.huyen.inventory_management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.ImportOrderDetail;

@Repository
public interface ImportOrderDetailRepository extends JpaRepository<ImportOrderDetail, UUID>{
    Optional<ImportOrderDetail> findByIdAndDeletedFalse(UUID id);
  
}
