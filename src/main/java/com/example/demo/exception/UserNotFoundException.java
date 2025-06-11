package com.example.demo.exception;

/**
 * Error message for when a user is not found
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
