package com.huyen.inventory_management.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.ImportOrderDetailDto;
import com.huyen.inventory_management.dto.ImportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ImportOrderDetailUpdateDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.mapper.ImportOrderMapper;
import com.huyen.inventory_management.model.ImportOrder;
import com.huyen.inventory_management.model.ImportOrderDetail;
import com.huyen.inventory_management.model.Material;
import com.huyen.inventory_management.repository.ImportOrderDetailRepository;
import com.huyen.inventory_management.repository.ImportOrderRepository;
import com.huyen.inventory_management.repository.MaterialRepository;
import com.huyen.inventory_management.service.ImportOrderDetailService;

@Service
public class ImportOrderDetailServiceImpl implements ImportOrderDetailService {

    @Autowired
    private ImportOrderDetailRepository importOrderDetailRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ImportOrderRepository importOrderRepository;

    @Override
    public ImportOrderDetailResponseDto createImportOrderDetail(ImportOrderDetailDto importOrderDetailDto) {
        ImportOrderDetail importOrderDetail = new ImportOrderDetail();

        Material material = materialRepository.findByIdAndDeletedFalse(importOrderDetailDto.getMaterialId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy vật liệu!"));
        ImportOrder importOrder = importOrderRepository.findByIdAndDeletedFalse(importOrderDetailDto.getImportOrderId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu nhập kho!"));
        importOrderDetail.setMaterial(material);
        importOrderDetail.setQuantity(importOrderDetailDto.getQuantity());
        importOrderDetail.setUnitPrice(importOrderDetailDto.getUnitPrice());

        importOrderDetailRepository.save(importOrderDetail);

        return ImportOrderMapper.toDetailResponseDto(importOrderDetail);
    }

    @Override
    public ImportOrderDetailResponseDto updateImportOrderDetail(UUID id,
            ImportOrderDetailUpdateDto importOrderDetailUpdateDto) {
        ImportOrderDetail importOrderDetail = importOrderDetailRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy chi tiết phiếu nhập kho!"));

        if (importOrderDetailUpdateDto.getMaterialId() != null) {
            Material material = materialRepository.findByIdAndDeletedFalse(importOrderDetailUpdateDto.getMaterialId())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy vật liệu!"));
            importOrderDetail.setMaterial(material);
        }
        if (importOrderDetailUpdateDto.getQuantity() != null && importOrderDetailUpdateDto.getQuantity() > 0) {
            importOrderDetail.setQuantity(importOrderDetailUpdateDto.getQuantity());
        }
        if (importOrderDetailUpdateDto.getUnitPrice() != null && importOrderDetailUpdateDto.getUnitPrice() > 0) {
            importOrderDetail.setUnitPrice(importOrderDetailUpdateDto.getUnitPrice());
        }

        importOrderDetailRepository.save(importOrderDetail);

        return ImportOrderMapper.toDetailResponseDto(importOrderDetail);
    }
    
    @Override
    public void deleteImportOrderDetail(UUID id) {
        ImportOrderDetail importOrderDetail = importOrderDetailRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy chi tiết phiếu nhập kho!"));
        importOrderDetail.setDeleted(true);

       importOrderDetailRepository.save(importOrderDetail); 
    }
}
