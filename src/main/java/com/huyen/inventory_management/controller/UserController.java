package com.huyen.inventory_management.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyen.inventory_management.dto.UserResponseDto;
import com.huyen.inventory_management.dto.UserUpdateDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.mapper.UserMapper;
import com.huyen.inventory_management.model.User;
import com.huyen.inventory_management.payload.ResponseData;
import com.huyen.inventory_management.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getUser(@PathVariable UUID id) {
        Optional<User> user = userService.getUser(id);
        
        if (user.isPresent()) {
            UserResponseDto responseDto = UserMapper.toResponseDto(user.get());

            ResponseData response = new ResponseData( 
                HttpStatus.OK.value(), 
                true, 
                "Lấy thông tin chi tiết nhân viên!",
                responseDto
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NotFoundException("Không tìm thấy nhân viên với ID: " + id);
        }
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseData> updateUser(@PathVariable UUID id, @RequestBody UserUpdateDto userUpdateDto) {
        User user = userService.updateUser(id, userUpdateDto);
        UserResponseDto responseDto = UserMapper.toResponseDto(user);

        ResponseData response = new ResponseData(
                HttpStatus.OK.value(),
                true,
                "Cập nhật thông tin nhân viên thành công!",
                responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        ResponseData response = new ResponseData( 
            HttpStatus.OK.value(), 
            true, 
            "Xoá nhân viên thành công!", 
            null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
