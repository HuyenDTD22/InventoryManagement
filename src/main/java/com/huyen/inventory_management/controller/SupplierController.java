package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.SupplierDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.model.Supplier;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.SupplierService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired  
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ResponseData> createSupplier(@Valid @RequestBody SupplierDto supplierDto) {
        Supplier supplier = supplierService.createSupplier(supplierDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo mới nhà cung cấp thành công!",
                supplier);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách nhà cung cấp thành công!",
                suppliers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getSupplier(@PathVariable UUID id) {
        Supplier supplier = supplierService.getSupplier(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(), 
                true, 
                "Lấy thông tin nhà cung cấp thành công!", 
                supplier
            );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateSupplier(@RequestBody SupplierDto supplierDto, @PathVariable UUID id) {
        Supplier supplier = supplierService.updateSupplier(supplierDto, id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Cập nhật thông tin nhà cung cấp thành công!",
                supplier);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteSupplier(@PathVariable UUID id) {
        supplierService.deleteSupplier(id);

        ResponseData response = new ResponseData(
            HttpStatus.OK.value(), 
            true, 
            "Xoá nhà cung cấp thành công!", 
            null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
