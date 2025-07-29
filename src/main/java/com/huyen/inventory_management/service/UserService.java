package com.huyen.inventory_management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.huyen.inventory_management.dto.UserUpdateDto;
import com.huyen.inventory_management.model.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUser(UUID id);

    User updateUser(UUID id, UserUpdateDto userUpdateDto);

    void deleteUser(UUID id);
}
