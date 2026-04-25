package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User createUser(RegisterRequest request);
    User getUserById(Long userId);
}
