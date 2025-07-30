package com.huyen.inventory_management.service;

import java.util.List;
import java.util.UUID;

import com.huyen.inventory_management.dto.SupplierDto;
import com.huyen.inventory_management.model.Supplier;

public interface SupplierService {
    Supplier createSupplier(SupplierDto supplierDto);
    
    List<Supplier> getAllSuppliers();

    Supplier getSupplier(UUID id);

    Supplier updateSupplier(SupplierDto supplierDto, UUID id);

    void deleteSupplier(UUID id);
}
