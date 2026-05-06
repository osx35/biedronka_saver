package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    Optional<Group> findByJoinCode(String joinCode);
    Optional<Group> findById(UUID uuid);
    Optional<Group> findByIdAndJoinCode(UUID uuid, String joinCode);
}
