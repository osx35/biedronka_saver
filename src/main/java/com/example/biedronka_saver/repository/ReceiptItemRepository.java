package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.ReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptItemRepository extends JpaRepository<ReceiptItem, Long> {
}
