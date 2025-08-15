package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.ImportOrderDto;
import com.huyen.inventory_management.dto.ImportOrderResponseDto;
import com.huyen.inventory_management.dto.ImportOrderUpdateDto;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.ImportOrderService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/import-orders")
public class ImportOrderController {

    @Autowired
    private ImportOrderService importOrderService;

    @PostMapping
    public ResponseEntity<ResponseData> createImportOrder(@RequestBody ImportOrderDto importOrderDto) {
        ImportOrderResponseDto responseDto = importOrderService.createImportOrder(importOrderDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo phiếu nhập hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getImportOrder(@Valid @PathVariable UUID id) {
        ImportOrderResponseDto responseDto = importOrderService.getImportOrder(id);

        ResponseData response = new ResponseData(
            HttpStatus.OK.value(), 
            true, 
            "Lấy thông tin chi tiết phiếu nhập hàng thành công!", 
            responseDto
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> getAllImportOrders() {
        List<ImportOrderResponseDto> responseDto = importOrderService.getAllImportOrders();

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách phiếu nhập hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateImportOrder(@Valid @PathVariable UUID id,
            @RequestBody ImportOrderUpdateDto importOrderUpdateDto) {
        ImportOrderResponseDto responseDto = importOrderService.updateImportOrder(id, importOrderUpdateDto);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Cập nhật thông tin phiếu nhập hàng thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteImportOrder(@Valid @PathVariable UUID id) {
        importOrderService.deleteImportOrder(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Xoá phiếu nhập hàng thành công!",
                null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
