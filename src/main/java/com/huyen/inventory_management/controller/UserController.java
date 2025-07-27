package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.UserResponseDto;
import com.huyen.inventory_management.mapper.UserMapper;
import com.huyen.inventory_management.model.User;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> responseDtoList = users.stream()
                .map(UserMapper::toResponseDto)
                .collect(Collectors.toList());

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Lấy danh sách nhân viên thành công!",
                responseDtoList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    

}
