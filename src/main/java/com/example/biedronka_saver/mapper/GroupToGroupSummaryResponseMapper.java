package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.dto.response.GroupSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class}
)
public interface GroupToGroupSummaryResponseMapper {
    @Mapping(target = "membersNames", source = "id", qualifiedByName = "getAllGroupMembersNames")
    GroupSummaryResponse toGroupSummaryResponse(Group group);
}
