package com.huyen.inventory_management.service;

import com.huyen.inventory_management.dto.LoginRequestDto;
import com.huyen.inventory_management.dto.RegisterRequestDto;
import com.huyen.inventory_management.model.User;

public interface AuthService {
    User register(RegisterRequestDto registerRequestDto);

    String login(LoginRequestDto loginRequestDto);
}
