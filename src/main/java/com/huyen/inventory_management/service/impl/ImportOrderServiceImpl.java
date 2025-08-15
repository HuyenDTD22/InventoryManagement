package com.huyen.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.ImportOrderDto;
import com.huyen.inventory_management.dto.ImportOrderResponseDto;
import com.huyen.inventory_management.dto.ImportOrderUpdateDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.mapper.ImportOrderMapper;
import com.huyen.inventory_management.model.ImportOrder;
import com.huyen.inventory_management.model.ImportOrderDetail;
import com.huyen.inventory_management.model.Material;
import com.huyen.inventory_management.model.Supplier;
import com.huyen.inventory_management.model.User;
import com.huyen.inventory_management.model.Warehouse;
import com.huyen.inventory_management.repository.ImportOrderDetailRepository;
import com.huyen.inventory_management.repository.ImportOrderRepository;
import com.huyen.inventory_management.repository.MaterialRepository;
import com.huyen.inventory_management.repository.SupplierRepository;
import com.huyen.inventory_management.repository.UserRepository;
import com.huyen.inventory_management.repository.WarehouseRepository;
import com.huyen.inventory_management.service.ImportOrderService;
import com.huyen.inventory_management.util.OrderCodeGenerator;

@Service
public class ImportOrderServiceImpl implements ImportOrderService {

    @Autowired
    private ImportOrderRepository importOrderRepository;

    @Autowired
    private ImportOrderDetailRepository importOrderDetailRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private OrderCodeGenerator orderCodeGenerator;

    @Override
    public ImportOrderResponseDto createImportOrder(ImportOrderDto importOrderDto) {
        Supplier supplier = supplierRepository.findByIdAndDeletedFalse(importOrderDto.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier not found"));
        Warehouse warehouse = warehouseRepository.findByIdAndDeletedFalse(importOrderDto.getWarehouseId())
                .orElseThrow(() -> new NotFoundException("Warehouse not found"));
        User user = userRepository.findByIdAndDeletedFalse(importOrderDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        ImportOrder importOrder = new ImportOrder();
        importOrder.setCode(orderCodeGenerator.generateOrderCode(OrderCodeGenerator.OrderType.IMPORT));
        importOrder.setPaymentStatus(importOrderDto.getPaymentStatus());
        importOrder.setSupplier(supplier);
        importOrder.setWarehouse(warehouse);
        importOrder.setUser(user);
        importOrder.setCreatedAt(LocalDateTime.now());
        importOrder.setDeleted(false);

        ImportOrder savedImportOrder = importOrderRepository.save(importOrder);

        List<ImportOrderDetail> importOrderDetails = importOrderDto.getImportOrderDetails().stream()
                .map(importOrderDetailDto -> {
                    Material material = materialRepository.findByIdAndDeletedFalse(importOrderDetailDto.getMaterialId())
                            .orElseThrow(() -> new NotFoundException("Material not found!"));
                    if (importOrderDetailDto.getQuantity() <= 0) {
                        throw new IllegalArgumentException("Quantity must be positive!");
                    }
                    if (importOrderDetailDto.getUnitPrice() < 0) {
                        throw new IllegalArgumentException("Unit price cannot be negative!");
                    }

                    ImportOrderDetail importOrderDetail = new ImportOrderDetail();
                    importOrderDetail.setImportOrder(savedImportOrder);
                    importOrderDetail.setMaterial(material);
                    importOrderDetail.setQuantity(importOrderDetailDto.getQuantity());
                    importOrderDetail.setUnitPrice(importOrderDetailDto.getUnitPrice());
                    importOrderDetail.setDeleted(false);

                    return importOrderDetail;
                }).collect(Collectors.toList());

        importOrderDetailRepository.saveAll(importOrderDetails);
        savedImportOrder.setImportOrderDetails(importOrderDetails);

        Float totalAmount = importOrderDetails.stream()
                .map(importOrderDetail -> importOrderDetail.getQuantity() * importOrderDetail.getUnitPrice())
                .reduce(0.0f, Float::sum);
        savedImportOrder.setTotalAmount(totalAmount);

        importOrderRepository.save(savedImportOrder);

        return ImportOrderMapper.toResponseDto(savedImportOrder);
    }
    
    @Override 
    public ImportOrderResponseDto getImportOrder(UUID id) {
        ImportOrder importOrder = importOrderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu nhập hàng!"));

        return ImportOrderMapper.toResponseDto(importOrder);
    }

    @Override
    public List<ImportOrderResponseDto> getAllImportOrders() {
        return importOrderRepository.findByDeletedFalse()
                .stream()
                .map(ImportOrderMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImportOrderResponseDto updateImportOrder(UUID id, ImportOrderUpdateDto importOrderUpdateDto) {
        ImportOrder importOrder = importOrderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu nhập hàng!"));

        if (importOrderUpdateDto.getPaymentStatus() != null) {
            importOrder.setPaymentStatus(importOrderUpdateDto.getPaymentStatus());
        }
        if (importOrderUpdateDto.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findByIdAndDeletedFalse(importOrderUpdateDto.getSupplierId())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp!"));
            importOrder.setSupplier(supplier);
        }
        if (importOrderUpdateDto.getUserId() != null) {
            User user = userRepository.findByIdAndDeletedFalse(importOrderUpdateDto.getUserId())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy nhân viên!"));
            importOrder.setUser(user);
        }
        if (importOrderUpdateDto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findByIdAndDeletedFalse(importOrderUpdateDto.getWarehouseId())
                    .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà kho!"));
            importOrder.setWarehouse(warehouse);
        }

        importOrderRepository.save(importOrder);

        return ImportOrderMapper.toSummaryDto(importOrder);
    }

    @Override
    public void deleteImportOrder(UUID id) {
        ImportOrder importOrder = importOrderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu nhập hàng!"));
        importOrder.setDeleted(true);

        importOrderRepository.save(importOrder);
    }
}
