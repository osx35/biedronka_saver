package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
