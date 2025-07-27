package com.huyen.inventory_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequestDto {
    @Email(message = "username must be a valid email")
    @NotBlank(message = "username is required")
    @Size(max = 255, message = "username must not exceed 255 characters")
    private String username;

    @NotBlank(message = "password is requied")
    @Size(min = 8, max = 255, message = "password must be between 8 and 255 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&^#\\[\\](){}|:;\"'<>,./~`+=_-]).*$",
        message = "Password must include at least one lowercase letter, one uppercase letter, one number, and one special character"         
    )
    private String password;

    @NotBlank(message = "fullName is required")
    @Size(max = 255, message = "fullName must not exceed 255 characters")
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
