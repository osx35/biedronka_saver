package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String productName);
}
