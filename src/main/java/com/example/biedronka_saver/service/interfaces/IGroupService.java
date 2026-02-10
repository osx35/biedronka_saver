package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.GroupCreationRequest;
import com.example.biedronka_saver.model.entity.Group;

import java.util.List;

public interface IGroupService {
    Group createGroup(GroupCreationRequest request);

    Group getGroupById(Long groupId);
    List<Group> getAllGroups();
    List<Group> getAllGroupsByMember(String name);

    Group updateGroup(Long id, GroupCreationRequest request);

    void deleteGroup(Long groupId);
}
