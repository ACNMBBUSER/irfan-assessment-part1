package com.irfan.resumebe.Exception;

public class UserNotFoundException extends RuntimeException  {

    public UserNotFoundException(String message) {
        super(message);
    }
}
