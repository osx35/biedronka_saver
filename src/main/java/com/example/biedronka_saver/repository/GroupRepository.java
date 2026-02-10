package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.Group;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> getGroupsByMembersContains(String member);

    boolean existsByNameAndId(String name, Long id);
}
