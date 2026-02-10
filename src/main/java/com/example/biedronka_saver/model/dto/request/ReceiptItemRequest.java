package com.example.biedronka_saver.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ReceiptItemRequest {
    @JsonProperty("name")
    private String productName;

    private BigDecimal quantity;

    private BigDecimal price;
}
