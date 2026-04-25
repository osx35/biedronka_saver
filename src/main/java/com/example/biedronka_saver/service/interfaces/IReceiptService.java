package com.example.biedronka_saver.service.interfaces;

import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.dto.request.ReceiptCreateRequest;

import java.util.List;

public interface IReceiptService {
    Receipt createReceipt(ReceiptCreateRequest receiptCreateRequest);

    Receipt getReceiptById(Long receiptId);
    List<Receipt> getAllReceipts();
    List<Receipt> getAllReceiptsByOwnerId(Long ownerId);

    Receipt updateReceipt(Receipt receipt);

    void deleteReceipt(Long receiptId);
}
