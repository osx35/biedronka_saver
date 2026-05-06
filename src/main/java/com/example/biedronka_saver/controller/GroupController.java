package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import com.example.biedronka_saver.model.dto.response.GroupCreationResponse;
import com.example.biedronka_saver.model.dto.response.GroupPreview;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.service.interfaces.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final IGroupService groupService;

    @PostMapping()
    public ResponseEntity<JSendResponse<GroupCreationResponse>> addGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
        GroupCreationResponse response = groupService.createGroup(groupCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(JSendResponse.success("Group created successfully",response));
    }

    @GetMapping("/{id}/join/{joinCode}")
    public ResponseEntity<JSendResponse<GroupPreview>> getGroupByUuiAndJoinCode(@PathVariable UUID id, @PathVariable String joinCode) {
        GroupPreview response = groupService.getGroupByUuidAndJoinCode(id, joinCode);
        return ResponseEntity.ok(JSendResponse.success("Group retrieved successfully", response));
    }
}
