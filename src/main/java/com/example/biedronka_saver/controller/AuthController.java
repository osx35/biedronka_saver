package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.dto.request.SignInRequest;
import com.example.biedronka_saver.model.dto.response.RegisterResponse;
import com.example.biedronka_saver.model.dto.response.SignInResponse;
import com.example.biedronka_saver.model.dto.response.UserInfoResponse;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/user")
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<JSendResponse<SignInResponse>>  signInUser(@Valid @RequestBody SignInRequest request){
        SignInResponse response = authService.signIn(request);
        return ResponseEntity.ok(JSendResponse.success("User logged in successfully", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@AuthenticationPrincipal User user){
        authService.logout(user);
        return ResponseEntity.ok("Logout successful.");
    }

    @GetMapping("/me")
    public ResponseEntity<JSendResponse<UserInfoResponse>> getCurrentUser(@AuthenticationPrincipal User user){
        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().toString())
                .build();
        return ResponseEntity.ok(JSendResponse.success("User details retrieved successfully", userInfoResponse));
    }

}
