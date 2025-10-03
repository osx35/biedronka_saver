package com.example.biedronka_saver.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInRequest {
    @NotNull(message = "Enter the username.")
    private String username;

    @NotNull(message = "Enter the password.")
    private String password;
}
