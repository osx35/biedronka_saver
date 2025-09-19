package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
