package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.dto.request.SignInRequest;
import com.example.biedronka_saver.model.dto.response.UserSummaryResponse;
import com.example.biedronka_saver.model.dto.response.SignInResponse;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @Override
    public SignInResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) userService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtil.generateToken(user.getUsername());
        log.info("User {} successfully signed in", user.getUsername());
        return SignInResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .build();
    }

    @Override
    public UserSummaryResponse register(RegisterRequest request) {
        User user = userService.createUser(request);
        return me(user);
    }

    @Override
    public UserSummaryResponse me(User user) {
        return UserSummaryResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole().toString())
                .build();
    }

    @Override
    public void logout(User user) {
        log.info("User {} has logged out", user.getUsername());
    }
}
