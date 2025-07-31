package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.WarehouseDto;
import com.huyen.inventory_management.model.Warehouse;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.WarehouseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired 
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<ResponseData> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseService.createWarehouse(warehouseDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo mới nhà kho thành công!",
                warehouse);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách nhà kho thành công!",
                warehouses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getWarehouse(@PathVariable UUID id) {
        Warehouse warehouse = warehouseService.getWarehouse(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(), 
                true, 
                "Lấy thông tin nhà kho thành công!", 
                warehouse
            );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateWarehouse(@RequestBody WarehouseDto warehouseDto, @PathVariable UUID id) {
        Warehouse warehouse = warehouseService.updateWarehouse(warehouseDto, id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Cập nhật thông tin nhà kho thành công!",
                warehouse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteWarehouse(@PathVariable UUID id) {
        warehouseService.deleteWarehouse(id);

        ResponseData response = new ResponseData(
            HttpStatus.OK.value(), 
            true, 
            "Xoá nhà kho thành công!", 
            null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
