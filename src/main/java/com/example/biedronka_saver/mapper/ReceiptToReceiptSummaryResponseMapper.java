package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.dto.response.ReceiptSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class}
)
public interface ReceiptToReceiptSummaryResponseMapper {
    @Mapping(target = "owner", source = "payer.displayName")
    ReceiptSummaryResponse toEntity(Receipt receipt);
}
