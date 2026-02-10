package com.example.biedronka_saver.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GroupCreationRequest {
    @NotNull(message = "Name cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Owner id cannot be null")
    private Long ownerId;

    @NotNull(message = "Members cannot be null")
    private List<String> members;
}
