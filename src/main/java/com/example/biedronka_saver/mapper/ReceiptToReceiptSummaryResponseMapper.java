package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.dto.response.ReceiptSummaryResponse;
import com.example.biedronka_saver.model.entity.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class}
)
public interface ReceiptToReceiptSummaryResponseMapper {
    @Mapping(target = "owner", source = "owner.username")
    @Mapping(target = "assignedUsers", source = "assignedUsers", qualifiedByName = "mapUsersToUsernames" )
    ReceiptSummaryResponse toEntity(Receipt receipt);
}
