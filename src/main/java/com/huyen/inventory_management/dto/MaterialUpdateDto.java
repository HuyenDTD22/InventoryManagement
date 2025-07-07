package com.huyen.inventory_management.dto;

import jakarta.validation.constraints.Size;

public class MaterialUpdateDto {
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Unit must not exceed 255 characters")
    private String unit;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
