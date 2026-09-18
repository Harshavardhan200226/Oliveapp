package com.normal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.normal.entity.User;
import com.normal.repository.UserRepository;
import com.normal.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Direct DTO Classes for Register & Login Request Payloads
    public static class AuthRequest {
        private String username;
        private String password;
        private String role; // Optional for login, required for register

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Default role setup
        String role = (request.getRole() != null && !request.getRole().isEmpty()) ? request.getRole() : "CUSTOMER";
        user.setRole(role);

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginReq) {
        System.out.println("DEBUG: Hit login controller for " + loginReq.getUsername());
        
        User user = userRepository.findByUsername(loginReq.getUsername());
        System.out.println("DEBUG: User found in DB? " + (user != null));

        if (user == null || !passwordEncoder.matches(loginReq.getPassword(), user.getPassword())) {
            System.out.println("DEBUG: Password match failed!");
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials!"));
        }
		return null;
    }
}