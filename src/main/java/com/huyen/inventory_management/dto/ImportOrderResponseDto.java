package com.huyen.inventory_management.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ImportOrderResponseDto {
    private UUID id;

    private String code;

    private Boolean paymentStatus;

    private LocalDateTime createdAt;

    private String supplier;

    private String warehouse;

    private String user;

    private List<ImportOrderDetailResponseDto> details;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<ImportOrderDetailResponseDto> getDetails() {
        return details;
    }

    public void setDetails(List<ImportOrderDetailResponseDto> details) {
        this.details = details;
    }
    
}
