package com.huyen.inventory_management.service;

import java.util.List;
import java.util.UUID;

import com.huyen.inventory_management.dto.WarehouseDto;
import com.huyen.inventory_management.model.Warehouse;

public interface WarehouseService {

    Warehouse createWarehouse(WarehouseDto warehouseDto);

    List<Warehouse> getAllWarehouses();

    Warehouse getWarehouse(UUID id);

    Warehouse updateWarehouse(WarehouseDto warehouseDto, UUID id);

    void deleteWarehouse(UUID id);
}
