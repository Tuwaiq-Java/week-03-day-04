package com.example.authsystem.exceptions;

public class UserIsAdminException extends RuntimeException {

    public UserIsAdminException(String message) {
        super(message);
    }
}
