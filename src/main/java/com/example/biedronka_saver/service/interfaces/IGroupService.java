package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupSummaryResponse;

public interface IGroupService {
    GroupSummaryResponse createGroup(GroupCreateRequest groupCreateRequest);
}
