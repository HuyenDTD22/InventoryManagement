package com.huyen.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDto {
    @NotBlank(message = "username is required")
    @Size(max = 255, message = "username is not exceed 255 characters")
    private String username;
    private String password;
    private String fullName;
}
