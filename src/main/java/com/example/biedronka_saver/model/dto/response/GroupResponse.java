package com.example.biedronka_saver.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GroupResponse {
    private Long id;
    private String name;
    private String description;
    private String owner;
    private List<String> members;
}
