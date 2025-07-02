package com.huyen.inventory_management.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "unit")
    private String unit;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "material")
    private List<ImportOrderDetail> importOrderDetails;

    @OneToMany(mappedBy = "material")
    private List<ExportOrderDetail> exportOrderDetails;

    @OneToMany(mappedBy = "material")
    private List<Inventory> inventories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ImportOrderDetail> getImportOrderDetails() {
        return importOrderDetails;
    }

    public void setImportOrderDetails(List<ImportOrderDetail> importOrderDetails) {
        this.importOrderDetails = importOrderDetails;
    }

    public List<ExportOrderDetail> getExportOrderDetails() {
        return exportOrderDetails;
    }

    public void setExportOrderDetails(List<ExportOrderDetail> exportOrderDetails) {
        this.exportOrderDetails = exportOrderDetails;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    
}
