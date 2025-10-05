package com.example.biedronka_saver.exception.implementation;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super("Passwords do not match.");
    }
}
