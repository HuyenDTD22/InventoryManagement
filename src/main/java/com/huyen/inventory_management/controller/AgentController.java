package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.AgentDto;
import com.huyen.inventory_management.model.Agent;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired  
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<ResponseData> createAgent(@Valid @RequestBody AgentDto agentDto) {
        Agent agent = agentService.createAgent(agentDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo mới nhà cung cấp thành công!",
                agent);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> getAllAgents() {
        List<Agent> agents = agentService.getAllAgents();

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách nhà cung cấp thành công!",
                agents);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getAgent(@PathVariable UUID id) {
        Agent agent = agentService.getAgent(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(), 
                true, 
                "Lấy thông tin nhà cung cấp thành công!", 
                agent
            );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateAgent(@RequestBody AgentDto agentDto, @PathVariable UUID id) {
        Agent agent = agentService.updateAgent(agentDto, id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Cập nhật thông tin nhà cung cấp thành công!",
                agent);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteAgent(@PathVariable UUID id) {
        agentService.deleteAgent(id);

        ResponseData response = new ResponseData(
            HttpStatus.OK.value(), 
            true, 
            "Xoá nhà cung cấp thành công!", 
            null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
