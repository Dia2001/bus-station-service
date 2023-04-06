package com.busstation.services;

import com.busstation.payload.request.LoginRequest;
import com.busstation.payload.request.SignupRequest;
import com.busstation.payload.response.ApiResponse;
import com.busstation.payload.response.JwtResponse;


public interface AuthService {
    JwtResponse signin(LoginRequest loginRequest);
    ApiResponse signup(SignupRequest signupRequest);
}
