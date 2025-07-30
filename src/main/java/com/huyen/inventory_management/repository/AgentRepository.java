package com.huyen.inventory_management.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.huyen.inventory_management.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, UUID> {

    boolean existsByNameAndDeletedFalse(String name);

    Optional<Agent> findByIdAndDeletedFalse(UUID id);

    List<Agent> findByDeletedFalse();
}
