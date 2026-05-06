package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.dto.response.GroupCreationResponse;
import com.example.biedronka_saver.model.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupToGroupCreationResponseMapper {
    GroupCreationResponse toGroupCreationResponse(Group group);
}
