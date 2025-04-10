package com.jbatrina.EmployeeManagementSystem.controller;

import com.jbatrina.EmployeeManagementSystem.dto.JwtAuthResponse;
import com.jbatrina.EmployeeManagementSystem.dto.LoginDto;
import com.jbatrina.EmployeeManagementSystem.entity.User;
import com.jbatrina.EmployeeManagementSystem.service.AuthService;
import com.jbatrina.EmployeeManagementSystem.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private AuthService authService;
    private UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginDto> signup(@Valid @RequestBody User user) {
        User registeredUser = userService.addNormalUser(user, user.getPassword());
        return new ResponseEntity<>(new LoginDto(registeredUser), HttpStatus.OK);
    }
}