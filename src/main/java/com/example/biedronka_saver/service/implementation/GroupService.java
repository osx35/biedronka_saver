package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.exception.implementation.GroupAlreadyExistsException;
import com.example.biedronka_saver.mapper.GroupCreationRequestToGroupMapper;
import com.example.biedronka_saver.model.dto.request.GroupCreationRequest;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.repository.GroupRepository;
import com.example.biedronka_saver.service.interfaces.IGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class GroupService implements IGroupService {
    private final GroupRepository groupRepository;
    private final GroupCreationRequestToGroupMapper mapper;

    @Override
    public Group createGroup(GroupCreationRequest request) {
        if(groupRepository.existsByNameAndId(request.getName(), request.getOwnerId())){
            throw new GroupAlreadyExistsException(request.getName(), request.getOwnerId());
        }
        return groupRepository.save(mapper.apply(request));
    }

    @Override
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroupsByMember(String name) {
        return groupRepository.getGroupsByMembersContains(name);
    }

    @Override
    public Group updateGroup(Long id, GroupCreationRequest request) {
        Group existingGroup = getGroupById(id);
        Group newGroup = mapper.apply(request);

        existingGroup.setName(newGroup.getName());
        existingGroup.setDescription(newGroup.getDescription());
        existingGroup.setOwner(newGroup.getOwner());
        existingGroup.setMembers(newGroup.getMembers());

        return groupRepository.save(existingGroup);
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
