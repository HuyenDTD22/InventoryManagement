package com.huyen.inventory_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyen.inventory_management.dto.UserUpdateDto;
import com.huyen.inventory_management.exception.NotFoundException;
import com.huyen.inventory_management.model.User;
import com.huyen.inventory_management.repository.UserRepository;
import com.huyen.inventory_management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByDeletedFalse();
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return userRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public User updateUser(UUID id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhân viên"));

        if (userUpdateDto.getFullName() != null) {
            user.setFullName(userUpdateDto.getFullName());
        }
        if (userUpdateDto.getAddress() != null) {
            user.setAddress(userUpdateDto.getAddress());
        }
        if (userUpdateDto.getDateOfBirth() != null) {
            user.setDateOfBirth(userUpdateDto.getDateOfBirth());
        }
        if (userUpdateDto.getUsername() != null) {
            user.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getPhone() != null) {
            user.setPhone(userUpdateDto.getPhone());
        }
        if (userUpdateDto.getStatus() != null) {
            user.setStatus(userUpdateDto.getStatus());
        }

        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhân viên!"));

        user.setDeleted(true);
        userRepository.save(user);
    }

}
