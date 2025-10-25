package com.example.biedronka_saver.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ReceiptCreateRequest {
    private String owner;

    private List<String> assignedUsers = new ArrayList<>();

    private String date;

    private String storeName;

    private List<ReceiptItemRequest> receiptItems = new ArrayList<>();

    private BigDecimal totalAmount;

    private BigDecimal discountSummary;
}
