package com.example.biedronka_saver.service.implementation;

import com.example.biedronka_saver.mapper.ReceiptCreateRequestToReceiptMapper;
import com.example.biedronka_saver.model.dto.request.ReceiptCreateRequest;
import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.repository.ReceiptRepository;
import com.example.biedronka_saver.service.interfaces.IReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ReceiptService implements IReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptCreateRequestToReceiptMapper receiptCreateRequestToReceiptMapper;

    @Override
    public Receipt createReceipt(ReceiptCreateRequest request) {
        Receipt receipt = receiptCreateRequestToReceiptMapper.toEntity(request);
        receiptRepository.save(receipt);
        return receipt;
    }

    @Override
    public Receipt getReceiptById(Long receiptId) {
        return receiptRepository.findById(receiptId)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public List<Receipt> getAllReceiptsByOwnerId(Long ownerId) {
        return receiptRepository.getReceiptsByOwner_Id(ownerId);
    }

    @Override
    public List<Receipt> getAllReceiptsAssignedToUser(Long userId) {
        return receiptRepository.getReceiptsByAssignedUsersId(userId);
    }

    @Override
    public List<Receipt> getAllReceiptsAssignedToUserButNotOwner(Long userid) {
        List<Receipt> receiptsAssigned = receiptRepository.getReceiptsByAssignedUsersId(userid);
        List<Receipt> receiptsOwned = receiptRepository.getReceiptsByOwner_Id(userid);
        return receiptsAssigned.stream().filter(receipt -> !receiptsOwned.contains(receipt)).toList();

    }

    @Override
    public Receipt updateReceipt(Receipt receipt) {
        return null;
    }

    @Override
    public void deleteReceipt(Long receiptId) {

    }
}
