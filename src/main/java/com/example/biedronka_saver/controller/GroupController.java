package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.mapper.GroupToGroupResponseMapper;
import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.dto.request.GroupCreationRequest;
import com.example.biedronka_saver.model.dto.response.GroupResponse;
import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.interfaces.IGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final IGroupService groupService;
    private final GroupToGroupResponseMapper mapper;

    @PostMapping
    public ResponseEntity<JSendResponse<GroupResponse>> createGroup(@Valid @RequestBody GroupCreationRequest request) {
        Group group = groupService.createGroup(request);
        GroupResponse response = mapper.apply(group);
        return ResponseEntity.ok(JSendResponse.success("Group created successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSendResponse<Group>> getGroupById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(JSendResponse.success("Group retrieved successfully", group));
    }

    @GetMapping
    public ResponseEntity<JSendResponse<List<Group>>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(JSendResponse.success("All groups retrieved successfully", groups));
    }

    @GetMapping("/my-groups")
    public ResponseEntity<JSendResponse<List<Group>>> getMyGroups(@AuthenticationPrincipal User user) {
        List<Group> groups = groupService.getAllGroupsByMember(user.getUsername());
        return ResponseEntity.ok(JSendResponse.success("User groups retrieved successfully", groups));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JSendResponse<Group>> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupCreationRequest request) {
        Group group = groupService.updateGroup(id, request);
        return ResponseEntity.ok(JSendResponse.success("Group updated successfully", group));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JSendResponse<Void>> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok(JSendResponse.success("Group deleted successfully", null));
    }

}
