package com.huyen.inventory_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDto {
    @Email(message = "username must be a valid email")
    @NotBlank(message = "username is required")
    @Size(max = 255, message = "username must not exceed 255 characters")
    private String username;

    @NotBlank(message = "password is required")
    @Size(max = 255, message = "password must be between 8 and 255 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
