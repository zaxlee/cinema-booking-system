package com.zaxlee.cinema.controller;

import com.zaxlee.cinema.dto.request.LoginRequest;
import com.zaxlee.cinema.dto.response.LoginResponse;
import com.zaxlee.cinema.model.User;
import com.zaxlee.cinema.security.JwtService;
import com.zaxlee.cinema.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService,
                          JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = userService.findByUsername(request.getUsername());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}