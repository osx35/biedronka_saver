package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findByJoinCode(String joinCode);
}
