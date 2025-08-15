package com.huyen.inventory_management.dto;

import java.util.UUID;

public class ImportOrderDetailUpdateDto {
    private UUID materialId;

    private Float quantity;

    private Float unitPrice;

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
