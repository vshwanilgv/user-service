package com.example.user_service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {

        super("User not found with id: " + id);
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}