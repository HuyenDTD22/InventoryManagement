package com.huyen.inventory_management.mapper;

import com.huyen.inventory_management.dto.MaterialResponseDto;
import com.huyen.inventory_management.model.Material;

public class MaterialMapper {
    
    public static MaterialResponseDto toResponseDto(Material material) {
        MaterialResponseDto dto = new MaterialResponseDto();

        dto.setId(material.getId());
        dto.setName(material.getName());
        dto.setCode(material.getCode());
        dto.setUnit(material.getUnit());
        dto.setDeleted(material.getDeleted());
        dto.setDescription(material.getDescription());
        dto.setCreatedAt(material.getCreatedAt());

        return dto;
    }
}
