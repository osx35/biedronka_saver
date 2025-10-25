package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final UserRepository userRepository;

    @Named("mapUsernameToUser")
    public User mapUsernameToUser(String username) {
        return username == null ? null : userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }

    @Named("mapUsersToUsernames")
    public List<String> mapUsersToUsernames(List<User> users) {
        return users.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Named("mapUsernamesToUsers")
    public List<User> mapUsernamesToUsers(List<String> usernames) {
        if (usernames == null) return new ArrayList<>();
        return usernames.stream()
                .map(this::mapUsernameToUser)
                .collect(Collectors.toList());
    }

    @Named("mapStringToLocalDateTime")
    public LocalDateTime mapStringToLocalDateTime(String date) {
        return date == null ? null : LocalDateTime.parse(date);
    }
}

