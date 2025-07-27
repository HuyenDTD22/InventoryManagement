package com.huyen.inventory_management.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyen.inventory_management.dto.LoginRequestDto;
import com.huyen.inventory_management.dto.RegisterRequestDto;
import com.huyen.inventory_management.exception.InvalidCredentialsException;
import com.huyen.inventory_management.exception.UsernameAlreadyExistsException;
import com.huyen.inventory_management.model.Role;
import com.huyen.inventory_management.model.User;
import com.huyen.inventory_management.repository.RoleRepository;
import com.huyen.inventory_management.repository.UserRepository;
import com.huyen.inventory_management.security.JwtTokenProvider;
import com.huyen.inventory_management.service.AuthService;
import com.huyen.inventory_management.util.PasswordEncoderUtil;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Override
    @Transactional
    public User register(RegisterRequestDto registerRequestDto) {
        if (userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("ROLE_USER");
                    return roleRepository.save(role);
                });

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setFullName(registerRequestDto.getFullName());
        user.setPassword(passwordEncoderUtil.encode(registerRequestDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setDeleted(false);
        user.setRole(defaultRole);

        return userRepository.save(user);
    }
    
    @Override
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsernameAndDeletedFalse(loginRequestDto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoderUtil.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return jwtTokenProvider.generateToken(user.getUsername());
    }
}
