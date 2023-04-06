package com.busstation.controller;

import com.busstation.payload.request.LoginRequest;
import com.busstation.payload.request.SignupRequest;
import com.busstation.payload.response.JwtResponse;
import com.busstation.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:/9999/")
@RestController(value = "authAPIofWeb")
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.signin(loginRequest), HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<>(  authService.signup(signupRequest),HttpStatus.CREATED);
    }
}
