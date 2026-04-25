package com.example.biedronka_saver.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class GroupCreateRequest {
    private String groupName;

    private List<String> groupMembersNames = new ArrayList<>();
}
