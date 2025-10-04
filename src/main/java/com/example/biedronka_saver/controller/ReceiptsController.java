package com.example.biedronka_saver.controller;

import com.example.biedronka_saver.model.dto.JSendResponse;
import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.service.interfaces.IReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/receipts")
public class ReceiptsController {
    private final IReceiptService receiptService;

    @GetMapping("/owner")
    public ResponseEntity<JSendResponse<List<Receipt>>> getReceiptsOwnedByUser(@AuthenticationPrincipal User user){
        List<Receipt> receipts = receiptService.getAllReceiptsByOwnerId(user.getId());
        return ResponseEntity.ok(JSendResponse.success("Receipts for owner",receipts));
    }

    @GetMapping("/user")
    public ResponseEntity<JSendResponse<List<Receipt>>> getReceiptsAssignedToUser(@AuthenticationPrincipal User user){
        List<Receipt> receipts = receiptService.getAllReceiptsAssignedToUserButNotOwner(user.getId());
        return ResponseEntity.ok(JSendResponse.success("Receipts for user but not owned",receipts));
    }
}
