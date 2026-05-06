package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupCreationResponse;
import com.example.biedronka_saver.model.dto.response.GroupPreview;
import com.example.biedronka_saver.model.entity.Group;

import java.util.UUID;

public interface IGroupService {
    GroupCreationResponse createGroup(GroupCreateRequest groupCreateRequest);
    GroupPreview getGroupByUuidAndJoinCode(UUID uuid, String joinCode);
}
