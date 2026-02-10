package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.dto.request.ReceiptCreateRequest;
import com.example.biedronka_saver.model.dto.request.ReceiptItemRequest;
import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.entity.ReceiptItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class, ProductMapper.class},
        builder = @Builder(disableBuilder = true)
)
public interface ReceiptCreateRequestToReceiptMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "owner", qualifiedByName = "mapUsernameToUser")
    @Mapping(target = "assignedUsers", source = "assignedUsers", qualifiedByName = "mapUsernamesToUsers")
    @Mapping(target = "date", source = "date", qualifiedByName = "mapStringToLocalDateTime")
    @Mapping(target = "receiptItems", qualifiedByName = "receiptItemRequestToReceiptItemList")
    @Mapping(target = "discountAmount", source = "discountSummary")
    Receipt toEntity(ReceiptCreateRequest receiptCreateRequest);

    @IterableMapping(qualifiedByName = "receiptItemRequestToReceiptItem")
    @Named("receiptItemRequestToReceiptItemList")
    List<ReceiptItem> receiptItemRequestToReceiptItemList(List<ReceiptItemRequest> list);

    @Named("receiptItemRequestToReceiptItem")
    @Mapping(target = "product", source = "productName", qualifiedByName = "toProduct")
    @Mapping(target = "pricePerUnit", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    ReceiptItem receiptItemRequestToReceiptItem(ReceiptItemRequest receiptItemRequest);

    @AfterMapping
    default void setReceiptForReceiptItems(@MappingTarget Receipt receipt) {
        Optional.ofNullable(receipt.getReceiptItems())
                .ifPresent(it -> it.forEach(item -> item.setReceipt(receipt)));
    }
}
