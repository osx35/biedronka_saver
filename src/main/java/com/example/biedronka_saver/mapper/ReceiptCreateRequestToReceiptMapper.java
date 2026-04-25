package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.entity.ReceiptItem;
import com.example.biedronka_saver.model.dto.request.ReceiptCreateRequest;
import com.example.biedronka_saver.model.dto.request.ReceiptItemRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {MapperHelper.class, ProductMapper.class},
        builder = @Builder(disableBuilder = true)
)
public interface ReceiptCreateRequestToReceiptMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "group", source = "groupId", qualifiedByName = "mapGroupIdToGroup")
    @Mapping(target = "payer", source = "payer", qualifiedByName = "mapDisplayNameToGroupMember")
    @Mapping(target = "date", source = "date", qualifiedByName = "mapStringToLocalDateTime")
    Receipt toEntity(ReceiptCreateRequest receiptCreateRequest);

    @IterableMapping(qualifiedByName = "receiptItemRequestToReceiptItem")
    @Named("receiptItemRequestToReceiptItemList")
    List<ReceiptItem> receiptItemRequestToReceiptItemList(List<ReceiptItemRequest> list);

    @Named("receiptItemRequestToReceiptItem")
    @Mapping(target = "product", source = "productName", qualifiedByName = "toProduct")
    @Mapping(target = "regularPricePerUnit", source = "price")
    ReceiptItem receiptItemRequestToReceiptItem(ReceiptItemRequest receiptItemRequest);

    @AfterMapping
    default void setReceiptForReceiptItems(@MappingTarget Receipt receipt) {
//        Optional.ofNullable(receipt.getReceiptItems())
//                .ifPresent(it -> it.forEach(item -> item.setReceipt(receipt)));
    }
}
