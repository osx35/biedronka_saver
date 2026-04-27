package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupSummaryResponse;
import com.example.biedronka_saver.model.entity.Group;

public interface IGroupService {
    GroupSummaryResponse createGroup(GroupCreateRequest groupCreateRequest);
    Group getGroupByJoinCode(String joinCode);
}
