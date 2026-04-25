package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.entity.GroupMember;
import com.example.biedronka_saver.repository.GroupMemberRepository;
import com.example.biedronka_saver.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;

    @Named("mapGroupMemberToDisplayName")
    public List<String> mapGroupMemberToDisplayName(List<GroupMember> groupMembers) {
        return groupMembers.stream()
                .map(GroupMember::getDisplayName)
                .collect(Collectors.toList());
    }

    @Named("mapDisplayNameToGroupMember")
    public GroupMember mapDisplayNameToGroupMember(String displayName) {
        return displayName == null ? null : groupMemberRepository.findByDisplayName(displayName)
                .orElseThrow(() -> new IllegalArgumentException("GroupMember not found: " + displayName));
    }


    @Named("mapGroupIdToGroup")
    public Group mapGroupIdToGroup(Long groupId) {
        return groupId == null ? null : groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
    }

    @Named("getAllGroupMembersNames")
    public List<String> getAllGroupMemberNames(Long groupId) {
        return groupId == null ? null : mapGroupMemberToDisplayName(groupMemberRepository.findAllByGroup_Id(groupId));
    }

    @Named("mapStringToLocalDateTime")
    public LocalDateTime mapStringToLocalDateTime(String date) {
        return date == null ? null : LocalDateTime.parse(date);
    }
}

