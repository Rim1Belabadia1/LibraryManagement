package com.example.demo.controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.adminModel.adminModel;
import com.example.demo.adminRepository.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private adminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody adminModel admin) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getAdminName(), admin.getAdminPassword()));
            String token = jwtUtil.generateToken(admin.getAdminName());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
