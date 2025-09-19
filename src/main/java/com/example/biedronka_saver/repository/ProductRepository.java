package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
