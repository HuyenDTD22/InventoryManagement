package com.huyen.inventory_management.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.ImportOrderDetailDto;
import com.huyen.inventory_management.dto.ImportOrderDetailResponseDto;
import com.huyen.inventory_management.dto.ImportOrderDetailUpdateDto;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.ImportOrderDetailService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/import-order-details")
public class ImportOrderDetailController {

    @Autowired
    private ImportOrderDetailService importOrderDetailService;

    @PostMapping
    public ResponseEntity<ResponseData> createImportOrderDetail(
            @Valid @RequestBody ImportOrderDetailDto importOrderDetailDto) {
        ImportOrderDetailResponseDto responseDto = importOrderDetailService
                .createImportOrderDetail(importOrderDetailDto);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Tạo chi tiết phiếu nhập kho thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PatchMapping
    public ResponseEntity<ResponseData> updateImportOrderDetail(@Valid @PathVariable UUID id,
            @RequestBody ImportOrderDetailUpdateDto importOrderDetailUpdateDto) {
        ImportOrderDetailResponseDto responseDto = importOrderDetailService.updateImportOrderDetail(id,
                importOrderDetailUpdateDto);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Chỉnh sửa chi tiết phiếu nhập kho thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseData> deleteImportOrderDetail(@Valid @PathVariable UUID id) {
        importOrderDetailService.deleteImportOrderDetail(id);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Xoá chi tiết phiếu nhập kho thành công!",
                null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
