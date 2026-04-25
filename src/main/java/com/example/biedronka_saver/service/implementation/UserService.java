package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.exception.implementation.PasswordsDoNotMatchException;
import com.example.biedronka_saver.exception.implementation.UserAlreadyExistsException;
import com.example.biedronka_saver.model.dto.request.RegisterRequest;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.model.enums.Role;
import com.example.biedronka_saver.repository.UserRepository;
import com.example.biedronka_saver.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public User createUser(RegisterRequest request) {
        String username = request.getUsername();
        String email = request.getEmail();

        if(userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("User with given username already exists.");
        }
        if(userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("User with given email already exists.");
        }
        if(!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);
        log.info("User {} successfully registered", user.getUsername());
        return savedUser;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User with given username does not exist."));
    }
}
