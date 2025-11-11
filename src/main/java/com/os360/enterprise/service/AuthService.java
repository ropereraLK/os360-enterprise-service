package com.os360.enterprise.service;

import com.os360.enterprise.dto.LoginRequest;
import com.os360.enterprise.dto.LoginResponse;
import com.os360.enterprise.entity.User;
import com.os360.enterprise.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // use BCryptPasswordEncoder
    private final JwtService jwtService; // your custom JWT generator

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse authenticate(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        User user = userOpt.orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(user);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setDisplayName(user.getFirstName() + " " + user.getLastName());
        response.setAccountLocked(user.isAccountLocked());
        response.setEnabled(user.isEnabled());

        return response;
    }
}
