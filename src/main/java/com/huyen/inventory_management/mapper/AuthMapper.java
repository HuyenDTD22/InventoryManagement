package com.huyen.inventory_management.mapper;

import com.huyen.inventory_management.dto.RegisterResponseDto;
import com.huyen.inventory_management.model.User;

public class AuthMapper {

    public static RegisterResponseDto registerResponseDto(User user) {
        RegisterResponseDto dto = new RegisterResponseDto();

        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setRole(user.getRole().getName());

        return dto;
    }
}
