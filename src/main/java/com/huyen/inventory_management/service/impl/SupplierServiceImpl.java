package com.huyen.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.SupplierDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.model.Supplier;
import com.huyen.inventory_management.repository.SupplierRepository;
import com.huyen.inventory_management.service.SupplierService;

@Service    
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier createSupplier(SupplierDto supplierDto) {
        if (supplierRepository.existsByNameAndDeletedFalse(supplierDto.getName())) {
            throw new IllegalArgumentException("Tên nhà cung cấp đã tồn tại!");
        }

        Supplier supplier = new Supplier();

        supplier.setName(supplierDto.getName());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setContactPerson(supplierDto.getContactPerson());
        supplier.setDeleted(false);
        supplier.setCreatedAt(LocalDateTime.now());

        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findByDeletedFalse();
    }
    
    @Override
    public Supplier getSupplier(UUID id) {
        return supplierRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));
    }

    @Override
    public Supplier updateSupplier(SupplierDto supplierDto, UUID id) {
        Supplier supplier = supplierRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));

        if (supplierDto.getName() != null) {
            supplier.setName(supplierDto.getName());
        }
        if (supplierDto.getAddress() != null) {
            supplier.setAddress(supplierDto.getAddress());
        }
        if (supplierDto.getEmail() != null) {
            supplier.setEmail(supplierDto.getEmail());
        }
        if (supplierDto.getPhone() != null) {
            supplier.setPhone(supplierDto.getPhone());
        }
        if (supplierDto.getContactPerson() != null) {
            supplier.setContactPerson(supplierDto.getContactPerson());
        }

        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(UUID id) {
        Supplier supplier = supplierRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));
        
        supplier.setDeleted(true);
        supplierRepository.save(supplier);
    }
}
