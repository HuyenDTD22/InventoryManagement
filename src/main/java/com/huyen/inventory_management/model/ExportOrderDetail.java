package com.huyen.inventory_management.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "export_order_details")
public class ExportOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Float unitPrice;

    @ManyToOne
    @JoinColumn(name = "export_order_id")
    private ExportOrder exportOrder;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ExportOrder getExportOrder() {
        return exportOrder;
    }

    public void setExportOrder(ExportOrder exportOrder) {
        this.exportOrder = exportOrder;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    
}
