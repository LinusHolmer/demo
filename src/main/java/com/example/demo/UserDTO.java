package com.example.demo;

import jakarta.validation.constraints.*;

/**
 *
 */

public class UserDTO {

  @NotBlank(message = "Username is required")
  @Size(min = 4, message = "Username must be at least 4 characters long")
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric only")
  private String username;

  @NotBlank(message = "Password is required")
  @Pattern(
          regexp = "^(?=.*[A-Z])(?=(?:.*\\d){2,})(?=(?:.*[!@#$%&*]){2,}).{8,}$",
          message = "Password must be at least 8 characters long, include 1 uppercase letter, 2 digits, and 2 special characters (!@#$%&*)"
  )
  private String password;

  @NotBlank(message = "Role is required")
  @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be either USER or ADMIN")
  private String role;

  @AssertTrue(message = "Consent is required to register")
  private boolean consentGiven;

  public UserDTO() {}

  public UserDTO(String username, String password, String role, boolean consentGiven) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.consentGiven = consentGiven;
  }

  public @NotBlank(message = "Username is required") @Size(min = 4, message = "Username must be at least 4 characters long") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric only") String getUsername() {
    return username;
  }

  public void setUsername(@NotBlank(message = "Username is required") @Size(min = 4, message = "Username must be at least 4 characters long") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric only") String username) {
    this.username = username;
  }

  public @NotBlank(message = "Password is required") @Pattern(
          regexp = "^(?=.*[A-Z])(?=(?:.*\\d){2,})(?=(?:.*[!@#$%&*]){2,}).{8,}$",
          message = "Password must be at least 8 characters long, include 1 uppercase letter, 2 digits, and 2 special characters (!@#$%&*)"
  ) String getPassword() {
    return password;
  }

  public void setPassword(@NotBlank(message = "Password is required") @Pattern(
          regexp = "^(?=.*[A-Z])(?=(?:.*\\d){2,})(?=(?:.*[!@#$%&*]){2,}).{8,}$",
          message = "Password must be at least 8 characters long, include 1 uppercase letter, 2 digits, and 2 special characters (!@#$%&*)"
  ) String password) {
    this.password = password;
  }

  public @NotBlank(message = "Role is required") @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be either USER or ADMIN") String getRole() {
    return role;
  }

  public void setRole(@NotBlank(message = "Role is required") @Pattern(regexp = "^(USER|ADMIN)$", message = "Role must be either USER or ADMIN") String role) {
    this.role = role;
  }

  @AssertTrue(message = "Consent is required to register")
  public boolean isConsentGiven() {
    return consentGiven;
  }

  public void setConsentGiven(@AssertTrue(message = "Consent is required to register") boolean consentGiven) {
    this.consentGiven = consentGiven;
  }
}
