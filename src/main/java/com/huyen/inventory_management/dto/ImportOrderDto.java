package com.huyen.inventory_management.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ImportOrderDto {
    @NotBlank(message = "Status is required")
    private Boolean paymentStatus;
    
    @NotNull(message = "Supplier ID is required")
    private UUID supplierId;

    @NotNull(message = "Warehouse ID is required")
    private UUID warehouseId;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Details ID is required")
    private List<ImportOrderDetailDto> importOrderDetails;

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<ImportOrderDetailDto> getImportOrderDetails() {
        return importOrderDetails;
    }

    public void setImportOrderDetails(List<ImportOrderDetailDto> importOrderDetails) {
        this.importOrderDetails = importOrderDetails;
    }

    
    
}
