package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.dto.request.SignInRequest;
import com.example.biedronka_saver.model.dto.response.RegisterResponse;
import com.example.biedronka_saver.model.dto.response.SignInResponse;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.interfaces.IAuthService;
import com.example.biedronka_saver.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public SignInResponse signIn(SignInRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return SignInResponse.builder()
                .token("token")
                .id(((User) userDetails).getId())
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        User user = userService.createUser(request);
        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    @Override
    public void logout(User user) {
        log.info("User {} has logged out", user.getUsername());
    }
}
