package com.example.demo;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
  private AppUser user;

    public UserDTO(AppUser user) {
        this.user = user;
    }

    @NotBlank(message = "Username is required")
    @Size(min = 4, message = "Username must be at least 4 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric only")
    private AppUser username;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=(?:.*\\d){2,})(?=(?:.*[!@#$%&*]){2,}).{8,}$",
            message = "Password must be at least 8 characters long, include 1 uppercase letter, 2 digits, and 2 special characters (!@#$%&*)"
    )
    private AppUser password;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be either USER or ADMIN")
    private AppUser role;


}

