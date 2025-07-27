package com.huyen.inventory_management.mapper;

import com.huyen.inventory_management.dto.UserResponseDto;
import com.huyen.inventory_management.model.User;

public class UserMapper {

    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setStatus(user.getStatus());

        return dto;
    }
}
