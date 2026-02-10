package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.mapper.ReceiptToReceiptSummaryResponseMapper;
import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.dto.request.ReceiptCreateRequest;
import com.example.biedronka_saver.model.dto.response.ReceiptSummaryResponse;
import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.interfaces.IReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/receipts")
public class ReceiptsController {
    private final IReceiptService receiptService;
    private final ReceiptToReceiptSummaryResponseMapper receiptToReceiptSummaryResponseMapper;

    @PostMapping()
    public ResponseEntity<JSendResponse<ReceiptSummaryResponse>> createReceipt(@RequestBody ReceiptCreateRequest receiptCreateRequest) {
        Receipt receipt =  receiptService.createReceipt(receiptCreateRequest);
        ReceiptSummaryResponse response = receiptToReceiptSummaryResponseMapper.toEntity(receipt);
        return ResponseEntity.ok(JSendResponse.success("Receipt created successfully", response));
    }

    @GetMapping("/owner")
    public ResponseEntity<JSendResponse<List<ReceiptSummaryResponse>>> getReceiptsOwnedByUser(@AuthenticationPrincipal User user){
        List<Receipt> receipts = receiptService.getAllReceiptsByOwnerId(user.getId());
        List<ReceiptSummaryResponse> responseList = receipts.stream().map(receiptToReceiptSummaryResponseMapper::toEntity).toList();
        return ResponseEntity.ok(JSendResponse.success("Receipts for owner",responseList));
    }

    @GetMapping("/user")
    public ResponseEntity<JSendResponse<List<ReceiptSummaryResponse>>> getReceiptsAssignedToUser(@AuthenticationPrincipal User user){
        List<Receipt> receipts = receiptService.getAllReceiptsAssignedToUserButNotOwner(user.getId());
        List<ReceiptSummaryResponse> responseList = receipts.stream().map(receiptToReceiptSummaryResponseMapper::toEntity).toList();
        return ResponseEntity.ok(JSendResponse.success("Receipts for user but not owned",responseList));
    }
}
