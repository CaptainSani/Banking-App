package com.sani.World.Banking.App.service;

import com.sani.World.Banking.App.payload.request.LoginRequest;
import com.sani.World.Banking.App.payload.request.UserRequest;
import com.sani.World.Banking.App.payload.response.APIResponse;
import com.sani.World.Banking.App.payload.response.BankResponse;
import com.sani.World.Banking.App.payload.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    BankResponse registerUser(UserRequest userRequest);

    ResponseEntity<APIResponse<JwtAuthResponse>> login(LoginRequest loginRequest);
}
