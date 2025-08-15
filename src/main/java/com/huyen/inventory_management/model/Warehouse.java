package com.huyen.inventory_management.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "warehouse")
    private List<ImportOrder> importOrders;

    @OneToMany(mappedBy = "warehouse")
    private List<ExportOrder> exportOrders;

    @OneToMany(mappedBy = "warehouse")
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<ImportOrder> getImportOrders() {
        return importOrders;
    }

    public void setImportOrders(List<ImportOrder> importOrders) {
        this.importOrders = importOrders;
    }

    public List<ExportOrder> getExportOrders() {
        return exportOrders;
    }

    public void setExportOrders(List<ExportOrder> exportOrders) {
        this.exportOrders = exportOrders;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    

    
}
