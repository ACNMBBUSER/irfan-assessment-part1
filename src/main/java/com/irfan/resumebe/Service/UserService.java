package com.irfan.resumebe.Service;


import com.irfan.resumebe.Exception.UserNotFoundException;
import com.irfan.resumebe.Model.User;
import com.irfan.resumebe.Repository.UserRepository;
import com.irfan.resumebe.jwt.JwtTokenResponse;
import com.irfan.resumebe.jwt.JwtTokenService;
import com.irfan.resumebe.jwt.UserRegistrationRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }

        log.info("Retrieved users list:");
        for (User user : users) {
            log.info("Users: {}", user.getUserName());
        }

        return users;

    }

    public JwtTokenResponse registerUser(UserRegistrationRequest registrationRequest) {
        // Encode password
        String encodedPassword = passwordEncoder.encode(registrationRequest.password());

        // Create UserDetails object with roles and authorities
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(registrationRequest.username())
                .password(encodedPassword)
                .roles("USER")  // Set user roles
                .authorities("read")  // Set user authorities
                .build();

        // Save the user
        User user = new User();
        user.setUserName(registrationRequest.username());
        user.setEmail(registrationRequest.email());
        user.setPassword(encodedPassword); // Use the encoded password
        user.setPhone_number(registrationRequest.phoneNumber());

        // Create authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Generate JWT token
        String token = jwtTokenService.generateToken(authentication);
        user.setJwtToken(token);
        // Set token expiration time (example: 1 day from now)
        user.setTokenExpiration(LocalDateTime.now().plusDays(1));

        userRepository.save(user);

        return new JwtTokenResponse(token);
    }
}
