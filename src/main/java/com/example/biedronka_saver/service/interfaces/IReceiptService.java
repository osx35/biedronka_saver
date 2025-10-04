package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.entity.Receipt;

import java.util.List;

public interface IReceiptService {
    Receipt createReceipt(Receipt receipt);

    Receipt getReceiptById(Long receiptId);
    List<Receipt> getAllReceipts();
    List<Receipt> getAllReceiptsByOwnerId(Long ownerId);
    List<Receipt> getAllReceiptsAssignedToUser(Long userId);
    List<Receipt> getAllReceiptsAssignedToUserButNotOwner(Long userid);

    Receipt updateReceipt(Receipt receipt);

    void deleteReceipt(Long receiptId);
}
