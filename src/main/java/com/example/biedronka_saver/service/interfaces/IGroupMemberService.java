package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.entity.GroupMember;

import java.util.List;

public interface IGroupMemberService {
    GroupMember createGroupMember(String groupMemberName, Group group);
    void setGroupForAllMembers(List<String> groupMemberNames, Group group);
}
