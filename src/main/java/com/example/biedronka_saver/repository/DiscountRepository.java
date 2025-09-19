package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
