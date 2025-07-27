package com.huyen.inventory_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.LoginRequestDto;
import com.huyen.inventory_management.dto.RegisterRequestDto;
import com.huyen.inventory_management.dto.RegisterResponseDto;
import com.huyen.inventory_management.mapper.AuthMapper;
import com.huyen.inventory_management.model.User;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthContrroller {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        User user = authService.register(registerRequestDto);
        RegisterResponseDto responseDto = AuthMapper.registerResponseDto(user);

        ResponseData response = new ResponseData(
                HttpStatus.CREATED.value(),
                true,
                "Đăng ký tài khoản thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto);

        ResponseData response = new ResponseData( 
            HttpStatus.OK.value(), 
            true, 
            "Đăng nhập thành công!",
            token
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<ResponseData> logout() {

        ResponseData response = new ResponseData( 
            HttpStatus.OK.value(), 
            true, 
            "Đăng xuất thành công!", 
            null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
