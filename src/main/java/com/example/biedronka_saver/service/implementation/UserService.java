package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.model.enums.Role;
import com.example.biedronka_saver.repository.UserRepository;
import com.example.biedronka_saver.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User with given username already exists.");
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User with given email already exists.");
        }
        if(!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Username not found"));
    }
}
