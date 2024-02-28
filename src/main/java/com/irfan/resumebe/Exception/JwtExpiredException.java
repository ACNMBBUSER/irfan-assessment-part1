package com.irfan.resumebe.Exception;

public class JwtExpiredException extends RuntimeException {

    public JwtExpiredException(String message) {
        super(message);
    }

    public JwtExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
