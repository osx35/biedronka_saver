package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.dto.response.GroupPreview;
import com.example.biedronka_saver.model.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class}
)
public interface GroupToGroupPreviewMapper {
    @Mapping(target = "membersNames", source = "id", qualifiedByName = "getAllGroupMembersNames")
    GroupPreview toGroupPreview(Group group);
}
