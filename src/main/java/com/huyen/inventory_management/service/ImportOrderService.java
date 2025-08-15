package com.huyen.inventory_management.service;

import java.util.List;
import java.util.UUID;

import com.huyen.inventory_management.dto.ImportOrderDto;
import com.huyen.inventory_management.dto.ImportOrderResponseDto;
import com.huyen.inventory_management.dto.ImportOrderUpdateDto;

public interface ImportOrderService {
    ImportOrderResponseDto createImportOrder(ImportOrderDto importOrderDto);

    ImportOrderResponseDto getImportOrder(UUID id);

    List<ImportOrderResponseDto> getAllImportOrders();

    ImportOrderResponseDto updateImportOrder(UUID id, ImportOrderUpdateDto importOrderUpdateDto);

    void deleteImportOrder(UUID id);
}
