package com.example.biedronka_saver.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class GroupPreview {
    private String name;
    private List<String> membersNames = new ArrayList<>();
}
