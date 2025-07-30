package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.MaterialDto;
import com.huyen.inventory_management.dto.MaterialResponseDto;
import com.huyen.inventory_management.dto.MaterialUpdateDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.mapper.MaterialMapper;
import com.huyen.inventory_management.model.Material;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.MaterialService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<ResponseData> createMaterial(@Valid @RequestBody MaterialDto materialDto) {
        Material material = materialService.createMaterial(materialDto);
        MaterialResponseDto responseDto = MaterialMapper.toResponseDto(material);

        ResponseData response = new ResponseData( 
            HttpStatus.CREATED.value(), 
            true,
            "Tạo vật liệu thành công!",
            responseDto
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getMaterial(@Valid @PathVariable UUID id) {
        Optional<Material> material = materialService.getMaterial(id);

        if (material.isPresent()) {
            MaterialResponseDto responseDto = MaterialMapper.toResponseDto(material.get());

            ResponseData response = new ResponseData(
                    HttpStatus.OK.value(),
                    true,
                    "Lấy dữ liệu thành công!",
                    responseDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NotFoundException("Không tìm thấy vật liệu với ID: " + id);
        }
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> getAllMaterials() {
        List<Material> materials = materialService.getAllMaterials();
        List<MaterialResponseDto> responseDtoList = materials.stream()
                .map(MaterialMapper::toResponseDto)
                .collect(Collectors.toList());

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách vật liệu thành công!",
                responseDtoList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateMaterial(@PathVariable UUID id, @RequestBody MaterialUpdateDto materialUpdateDto) {
        Material material = materialService.updateMaterial(id, materialUpdateDto);
        MaterialResponseDto responseDto = MaterialMapper.toResponseDto(material);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Cập nhật vật liệu thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteMaterial(@PathVariable UUID id) {
        materialService.deleteMaterial(id);

        ResponseData response = new ResponseData( 
            HttpStatus.OK.value(), 
            true,
            "Xoá vật liệu thành công!",
            null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
