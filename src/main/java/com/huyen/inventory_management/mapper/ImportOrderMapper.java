package com.huyen.inventory_management.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.huyen.inventory_management.dto.ImportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ImportOrderResponseDto;
import com.huyen.inventory_management.model.ImportOrder;
import com.huyen.inventory_management.model.ImportOrderDetail;

public class ImportOrderMapper {

    public static ImportOrderResponseDto toSummaryDto(ImportOrder importOrder) {
        ImportOrderResponseDto dto = new ImportOrderResponseDto();

        dto.setId(importOrder.getId());
        dto.setCode(importOrder.getCode());
        dto.setPaymentStatus(importOrder.getPaymentStatus());
        dto.setCreatedAt(importOrder.getCreatedAt());
        dto.setSupplier(importOrder.getSupplier().getName());
        dto.setUser(importOrder.getUser().getFullName());
        dto.setWarehouse(importOrder.getWarehouse().getName());

        return dto;
    }

    public static ImportOrderDetailResponseDto toDetailResponseDto(ImportOrderDetail importOrderDetail) {
        ImportOrderDetailResponseDto dto = new ImportOrderDetailResponseDto();

        dto.setId(importOrderDetail.getId());
        dto.setMaterial(importOrderDetail.getMaterial().getName());
        dto.setQuantity(importOrderDetail.getQuantity());
        dto.setUnitPrice(importOrderDetail.getUnitPrice());

        return dto;
    }

    public static ImportOrderResponseDto toResponseDto(ImportOrder importOrder) {
        ImportOrderResponseDto dto = toSummaryDto(importOrder);

        List<ImportOrderDetailResponseDto> detailDtos = importOrder.getImportOrderDetails()
            .stream()
            .map(ImportOrderMapper::toDetailResponseDto)
            .collect(Collectors.toList());

        dto.setDetails(detailDtos);

        return dto;
    }
}
