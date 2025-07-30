package com.huyen.inventory_management.service;

import java.util.List;
import java.util.UUID;

import com.huyen.inventory_management.dto.AgentDto;
import com.huyen.inventory_management.model.Agent;

public interface AgentService {
    Agent createAgent(AgentDto agentDto);
    
    List<Agent> getAllAgents();

    Agent getAgent(UUID id);

    Agent updateAgent(AgentDto agentDto, UUID id);

    void deleteAgent(UUID id);
}
