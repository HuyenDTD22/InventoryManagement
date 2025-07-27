package com.huyen.inventory_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    

}
