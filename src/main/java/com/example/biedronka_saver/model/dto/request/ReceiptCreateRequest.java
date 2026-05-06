package com.example.biedronka_saver.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReceiptCreateRequest {
    private UUID groupId;

    private String payer;

    private String date;

    private String storeName;

    private BigDecimal totalAmount;
}
