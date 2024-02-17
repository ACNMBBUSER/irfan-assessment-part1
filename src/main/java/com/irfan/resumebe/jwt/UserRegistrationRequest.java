package com.irfan.resumebe.jwt;

public record UserRegistrationRequest(String username, String password, String email, String phoneNumber) {
}
