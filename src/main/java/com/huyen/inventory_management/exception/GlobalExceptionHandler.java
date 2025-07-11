package com.huyen.inventory_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.huyen.inventory_management.payload.ResponseData;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseData> handleIllegalArgument(IllegalArgumentException ex) {
        ResponseData response = new ResponseData(
                HttpStatus.CONFLICT.value(),
                false,
                ex.getMessage(),
                null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseData> handleValidationException(ConstraintViolationException ex) {
        ResponseData response = new ResponseData( 
            HttpStatus.BAD_REQUEST.value(),
            false,
            "Dữ liệu không hợp lệ: " + ex.getMessage(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseData> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ResponseData response = new ResponseData(
                HttpStatus.BAD_REQUEST.value(),
                false,
                "Kiểu dữ liệu không hợp lệ cho tham số: " + ex.getName(),
                null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData> handleGenericException(Exception ex) {
        ResponseData response = new ResponseData( 
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            false,
            "Đã xảy ra lỗi hệ thống: " + ex.getMessage(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
