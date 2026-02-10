package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.dto.response.GroupResponse;
import com.example.biedronka_saver.model.entity.Group;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GroupToGroupResponseMapper implements Function<Group, GroupResponse> {
    @Override
    public GroupResponse apply(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .description(group.getDescription())
                .name(group.getName())
                .owner(group.getOwner().getUsername())
                .members(group.getMembers())
                .build();
    }
}
