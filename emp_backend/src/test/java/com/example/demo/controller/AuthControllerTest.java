package com.example.demo.controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.adminModel.adminModel;
import com.example.demo.adminRepository.adminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private adminRepository adminRepository;

    @InjectMocks
    private AuthController authController;

    private adminModel admin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialisation des mocks
        admin = new adminModel("admin1", "password1");
    }

    @Test
    public void testLoginSuccessful() {
        // Arrange
        String token = "valid-token";
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);  // Simuler une authentification réussie
        when(jwtUtil.generateToken(admin.getAdminName())).thenReturn(token);

        // Act
        ResponseEntity<?> response = authController.login(admin);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(token, response.getBody());
    }

    @Test
    public void testLoginInvalidCredentials() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Invalid credentials"));

        // Act
        ResponseEntity<?> response = authController.login(admin);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }
}
