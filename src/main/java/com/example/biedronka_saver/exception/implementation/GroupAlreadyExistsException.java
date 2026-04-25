package com.example.biedronka_saver.exception.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class GroupAlreadyExistsException extends RuntimeException {
    public GroupAlreadyExistsException(String name, Long userId) {
        super(String.format("Group with name '%s' already exists for user with ID %d.", name, userId));
    }
}
