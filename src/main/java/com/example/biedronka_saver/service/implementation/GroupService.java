package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.mapper.GroupCreateRequestToGroupMapper;
import com.example.biedronka_saver.mapper.GroupToGroupSummaryResponseMapper;
import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupSummaryResponse;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.repository.GroupRepository;
import com.example.biedronka_saver.service.interfaces.IGroupMemberService;
import com.example.biedronka_saver.service.interfaces.IGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class GroupService implements IGroupService {
    private final GroupRepository groupRepository;
    private final GroupCreateRequestToGroupMapper requestToGroupMapper;
    private final GroupToGroupSummaryResponseMapper groupToGroupSummaryResponseMapper;
    private final IGroupMemberService groupMemberService;

    @Override
    public GroupSummaryResponse createGroup(GroupCreateRequest request) {

        Group group = requestToGroupMapper.toEntity(request);
        group.setJoinCode(RandomStringUtils.secure().nextAlphanumeric(8).toUpperCase());
        group = groupRepository.save(group);

        groupMemberService.setGroupForAllMembers(request.getGroupMembersNames(), group);

        return groupToGroupSummaryResponseMapper.toGroupSummaryResponse(group);
    }

    @Override
    public Group getGroupByJoinCode(String joinCode) {
        return groupRepository.findByJoinCode(joinCode)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

}
