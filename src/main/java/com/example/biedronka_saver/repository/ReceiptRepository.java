package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> getReceiptsByPayer_Id(Long payer_id);
}
