package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.dto.request.SignInRequest;
import com.example.biedronka_saver.model.dto.response.RegisterResponse;
import com.example.biedronka_saver.model.dto.response.SignInResponse;
import com.example.biedronka_saver.model.entity.User;

public interface IAuthService {
    SignInResponse signIn(SignInRequest request);
    RegisterResponse register(RegisterRequest request);
    void logout(User user);
}
