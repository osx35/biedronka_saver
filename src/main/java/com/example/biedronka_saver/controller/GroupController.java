package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupSummaryResponse;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.service.interfaces.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final IGroupService groupService;

    @PostMapping()
    public ResponseEntity<JSendResponse<GroupSummaryResponse>> addGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
        GroupSummaryResponse response = groupService.createGroup(groupCreateRequest);
        return ResponseEntity.ok(JSendResponse.success("Group created successfully",response));
    }

    @GetMapping("/{joinCode}")
    public ResponseEntity<JSendResponse<Group>> getGroupByJoinCode(@PathVariable String joinCode) {
        Group response = groupService.getGroupByJoinCode(joinCode);
        return ResponseEntity.ok(JSendResponse.success("Group retrieved successfully",response));
    }
}
