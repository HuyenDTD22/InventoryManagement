package com.huyen.inventory_management.service;

import java.util.UUID;

import com.huyen.inventory_management.dto.ImportOrderDetailDto;
import com.huyen.inventory_management.dto.ImportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ImportOrderDetailUpdateDto;

public interface ImportOrderDetailService {
    ImportOrderDetailResponseDto createImportOrderDetail(ImportOrderDetailDto importOrderDetailDto);

    ImportOrderDetailResponseDto updateImportOrderDetail(UUID id,
            ImportOrderDetailUpdateDto importOrderDetailUpdateDto);

    void deleteImportOrderDetail(UUID id);
}
