package com.RestaurantManagementSystem.exceptions.userExceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}
