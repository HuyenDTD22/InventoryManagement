package com.huyen.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.MaterialDto;
import com.huyen.inventory_management.dto.MaterialUpdateDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.model.Material;
import com.huyen.inventory_management.repository.MaterialRepository;
import com.huyen.inventory_management.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Material createMaterial(MaterialDto materialDto) {
        if (materialRepository.existsByCode(materialDto.getCode())) {
            throw new IllegalArgumentException("Mã vật liệu đã tồn tại");
        }

        Material material = new Material();
        
        material.setName(materialDto.getName());
        material.setCode(materialDto.getCode());
        material.setUnit(materialDto.getUnit());
        material.setDescription(materialDto.getDescription());
        material.setCreatedAt(LocalDateTime.now());
        material.setDeleted(false);

        return materialRepository.save(material);
    }

    @Override
    public Optional<Material> getMaterial(UUID id) {
        return materialRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findByDeletedFalse();
    }

    @Override
    public Material updateMaterial(UUID id, MaterialUpdateDto materialUpdateDto) {
        Material material = materialRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy vật liệu"));

        if (materialUpdateDto.getName() != null) {
            material.setName(materialUpdateDto.getName());
        }
        if (materialUpdateDto.getUnit() != null) {
            material.setUnit(materialUpdateDto.getUnit());
        }
        if (materialUpdateDto.getDescription() != null) {
            material.setDescription(materialUpdateDto.getDescription());
        }

        return materialRepository.save(material);
    }
    
    @Override
    public void deleteMaterial(UUID id) {
        Material material = materialRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vật liệu với ID: " + id));

        material.setDeleted(true);
        materialRepository.save(material);
    }
    
}
