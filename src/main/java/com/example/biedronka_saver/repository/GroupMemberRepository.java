package com.example.biedronka_saver.repository;

import com.example.biedronka_saver.model.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {
    Optional<GroupMember> findByDisplayName(String displayName);

    List<GroupMember> findAllByGroup_Id(Long groupId);

}
