package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    boolean existsByName_AndId(String name, Long id);
}
