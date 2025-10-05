package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.dto.request.SignInRequest;
import com.example.biedronka_saver.model.dto.response.UserSummaryResponse;
import com.example.biedronka_saver.model.dto.response.SignInResponse;
import com.example.biedronka_saver.model.entity.User;

public interface IAuthService {
    SignInResponse signIn(SignInRequest request);
    UserSummaryResponse register(RegisterRequest request);
    UserSummaryResponse me(User user);
    void logout(User user);
}
