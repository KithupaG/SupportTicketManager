package com.spring.ems.service;

import com.spring.ems.dto.AuthResponse;
import com.spring.ems.dto.LoginRequest;
import com.spring.ems.dto.RegisterRequest;
import com.spring.ems.dto.UserResponse;
import com.spring.ems.model.*;
import com.spring.ems.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        String hashedPassword = passwordEncoder.encode(request.password());

        User user;
        switch(request.role()) {
            case CUSTOMER -> user = new Customer(request.email(),
                    hashedPassword,
                    request.firstName(),
                    request.lastName(),
                    Role.CUSTOMER,
                    request.phone(),
                    request.address());
            case ADMIN -> user = new Admin(request.email(),
                    hashedPassword, request.firstName(),
                    request.lastName(),
                    Role.ADMIN,
                    request.department(),
                    request.accessLevel());
            case SUPPORTER_AGENT -> user = new SupportAgent(request.email(),
                    hashedPassword,
                    request.firstName(),
                    request.lastName(),
                    Role.SUPPORTER_AGENT,
                    request.department(),
                    request.maxActiveTickets());
            default -> throw new RuntimeException("Invalid role");
        }

        userRepository.save(user);

        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("Invalid Credentials!"));
        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials!");
        }

        UserResponse ur = new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole()
        );

        return new AuthResponse(jwtService.generateToken(user), ur);
    }
}
