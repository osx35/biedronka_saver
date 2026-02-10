package com.example.biedronka_saver.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ReceiptSummaryResponse {
    private Long id;

    private String owner;

    private LocalDateTime date;

    private String storeName;

    private List<String> assignedUsers;

    private BigDecimal totalAmount;
}
