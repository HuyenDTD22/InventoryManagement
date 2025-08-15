package com.huyen.inventory_management.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class ImportOrderDetailDto {
    @NotNull(message = "Import order ID is required")
    private UUID ImportOrderId;

    @NotNull(message = "Material ID is required")
    private UUID materialId;

    @NotNull(message = "Quantity is required")
    private Float quantity;

    @NotNull(message = "Unit price is required")
    private Float unitPrice;

    public UUID getImportOrderId() {
        return ImportOrderId;
    }

    public void setImportOrderId(UUID importOrderId) {
        ImportOrderId = importOrderId;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
