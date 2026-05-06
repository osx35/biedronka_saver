package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.mapper.GroupCreateRequestToGroupMapper;
import com.example.biedronka_saver.mapper.GroupToGroupCreationResponseMapper;
import com.example.biedronka_saver.mapper.GroupToGroupPreviewMapper;
import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupCreationResponse;
import com.example.biedronka_saver.model.dto.response.GroupPreview;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.repository.GroupRepository;
import com.example.biedronka_saver.service.interfaces.IGroupMemberService;
import com.example.biedronka_saver.service.interfaces.IGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class GroupService implements IGroupService {
    private final GroupRepository groupRepository;
    private final GroupCreateRequestToGroupMapper requestToGroupMapper;
    private final GroupToGroupPreviewMapper groupToGroupSummaryResponseMapper;
    private final GroupToGroupCreationResponseMapper groupToGroupCreationResponseMapper;
    private final IGroupMemberService groupMemberService;

    @Override
    @Transactional
    public GroupCreationResponse createGroup(GroupCreateRequest request) {

        Group group = requestToGroupMapper.toEntity(request);
        group.setJoinCode(RandomStringUtils.secure().nextAlphanumeric(8).toUpperCase());
        group = groupRepository.save(group);

        groupMemberService.setGroupForAllMembers(request.getGroupMembersNames(), group);

        return groupToGroupCreationResponseMapper.toGroupCreationResponse(group);
    }

    @Override
    public GroupPreview getGroupByUuidAndJoinCode(UUID uuid, String joinCode) {
        return  groupToGroupSummaryResponseMapper.toGroupPreview(groupRepository.findByIdAndJoinCode(uuid, joinCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found or invalid link")));
    }

}
