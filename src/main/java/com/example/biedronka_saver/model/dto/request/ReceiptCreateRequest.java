package com.example.biedronka_saver.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ReceiptCreateRequest {
    private Long groupId;

    private String payer;

    private String date;

    private String storeName;

    private BigDecimal totalAmount;
}
