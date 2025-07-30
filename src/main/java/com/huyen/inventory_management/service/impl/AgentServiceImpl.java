package com.huyen.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.AgentDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.model.Agent;
import com.huyen.inventory_management.repository.AgentRepository;
import com.huyen.inventory_management.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent createAgent(AgentDto agentDto) {
        if (agentRepository.existsByNameAndDeletedFalse(agentDto.getName())) {
            throw new IllegalArgumentException("Tên nhà cung cấp đã tồn tại!");
        }

        Agent agent = new Agent();

        agent.setName(agentDto.getName());
        agent.setPhone(agentDto.getPhone());
        agent.setAddress(agentDto.getAddress());
        agent.setEmail(agentDto.getEmail());
        agent.setContactPerson(agentDto.getContactPerson());
        agent.setDeleted(false);
        agent.setCreatedAt(LocalDateTime.now());

        return agentRepository.save(agent);
    }

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findByDeletedFalse();
    }
    
    @Override
    public Agent getAgent(UUID id) {
        return agentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));
    }

    @Override
    public Agent updateAgent(AgentDto agentDto, UUID id) {
        Agent agent = agentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));

        if (agentDto.getName() != null) {
            agent.setName(agentDto.getName());
        }
        if (agentDto.getAddress() != null) {
            agent.setAddress(agentDto.getAddress());
        }
        if (agentDto.getEmail() != null) {
            agent.setEmail(agentDto.getEmail());
        }
        if (agentDto.getPhone() != null) {
            agent.setPhone(agentDto.getPhone());
        }
        if (agentDto.getContactPerson() != null) {
            agent.setContactPerson(agentDto.getContactPerson());
        }

        return agentRepository.save(agent);
    }

    @Override
    public void deleteAgent(UUID id) {
        Agent agent = agentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));
        
        agent.setDeleted(true);
        agentRepository.save(agent);
    }
}
