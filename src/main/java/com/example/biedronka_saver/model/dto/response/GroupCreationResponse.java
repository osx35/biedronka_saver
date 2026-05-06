package com.example.biedronka_saver.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class GroupCreationResponse {
    private UUID id;
    private String name;
    private String joinCode;
}
