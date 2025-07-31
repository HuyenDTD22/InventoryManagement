package com.huyen.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.WarehouseDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.model.Warehouse;
import com.huyen.inventory_management.repository.WarehouseRepository;
import com.huyen.inventory_management.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse createWarehouse(WarehouseDto warehouseDto) {
        if (warehouseRepository.existsByNameAndDeletedFalse(warehouseDto.getName())) {
            throw new IllegalArgumentException("Tên nhà kho đã tồn tại!");
        }

        Warehouse warehouse = new Warehouse();

        warehouse.setName(warehouseDto.getName());
        warehouse.setAddress(warehouseDto.getAddress());
        warehouse.setCreatedAt(LocalDateTime.now());
        warehouse.setDaeleted(false);

        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findByDeletedFalse();
    }

    @Override
    public Warehouse getWarehouse(UUID id) {
        return warehouseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy kho!"));
    }

    @Override
    public Warehouse updateWarehouse(WarehouseDto warehouseDto, UUID id) {
        Warehouse warehouse = warehouseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy kho"));

        if (warehouseDto.getName() != null) {
            warehouse.setName(warehouseDto.getName());
        }
        if (warehouseDto.getAddress() != null) {
            warehouse.setAddress(warehouseDto.getAddress());
        }

        return warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(UUID id) {
        Warehouse warehouse = warehouseRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy kho"));

        warehouse.setDaeleted(true);

        warehouseRepository.save(warehouse);
    }
}
