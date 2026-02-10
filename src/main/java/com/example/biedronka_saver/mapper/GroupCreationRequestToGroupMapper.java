package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.dto.request.GroupCreationRequest;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GroupCreationRequestToGroupMapper implements Function<GroupCreationRequest, Group> {
    private final UserService userService;
    @Override
    public Group apply(GroupCreationRequest groupCreationRequest) {
        User owner = userService.getUserById(groupCreationRequest.getOwnerId());
        List<String> members = groupCreationRequest.getMembers();
        members.add(owner.getUsername());

        return Group.builder()
                .name(groupCreationRequest.getName())
                .owner(owner)
                .description(groupCreationRequest.getDescription())
                .members(members)
                .build();
    }
}
