package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.dto.request.SignInRequest;
import com.example.biedronka_saver.model.dto.response.SignInResponse;
import com.example.biedronka_saver.model.dto.response.UserSummaryResponse;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<JSendResponse<UserSummaryResponse>> registerUser(@Valid @RequestBody RegisterRequest request){
        UserSummaryResponse response = authService.register(request);
        return ResponseEntity.ok(JSendResponse.success("User registered successfully", response));
    }

    @PostMapping("/signin")
    public ResponseEntity<JSendResponse<SignInResponse>>  signInUser(@Valid @RequestBody SignInRequest request){
        SignInResponse response = authService.signIn(request);
        return ResponseEntity.ok(JSendResponse.success("User logged in successfully", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<JSendResponse<Void>> logoutUser(@AuthenticationPrincipal User user){
        authService.logout(user);
        return ResponseEntity.ok(JSendResponse.success("User logged out", null));
    }

    @GetMapping("/me")
    public ResponseEntity<JSendResponse<UserSummaryResponse>> getCurrentUser(@AuthenticationPrincipal User user){
        UserSummaryResponse response = authService.me(user);
        return ResponseEntity.ok(JSendResponse.success("User details retrieved successfully", response));
    }

}
