package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.dto.request.GroupCreateRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class},
        builder = @Builder(disableBuilder = true)
)
public interface GroupCreateRequestToGroupMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "groupName")
    Group toEntity(GroupCreateRequest request);

}
