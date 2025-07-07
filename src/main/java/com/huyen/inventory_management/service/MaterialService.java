package com.huyen.inventory_management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huyen.inventory_management.dto.MaterialDto;
import com.huyen.inventory_management.dto.MaterialUpdateDto;
import com.huyen.inventory_management.model.Material;

public interface MaterialService {
    Material createMaterial(MaterialDto materialdto);
    
    Optional<Material> getMaterial(UUID id);

    List<Material> getAllMaterials();

    Material updateMaterial(UUID id, MaterialUpdateDto materialUpdateDto);

    void deleteMaterial(UUID id);
}
