package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.entity.GroupMember;
import com.example.biedronka_saver.repository.GroupMemberRepository;
import com.example.biedronka_saver.service.interfaces.IGroupMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class GroupMemberService implements IGroupMemberService {
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public GroupMember createGroupMember(String groupMemberName, Group group) {
        return groupMemberRepository.save(GroupMember.builder()
                .displayName(groupMemberName)
                .group(group)
                .build());
    }

    @Override
    public void setGroupForAllMembers(List<String> groupMemberNames, Group group) {
        groupMemberNames.forEach(groupMemberName -> createGroupMember(groupMemberName, group));
    }
}
