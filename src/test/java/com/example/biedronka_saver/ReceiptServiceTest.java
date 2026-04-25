package com.example.biedronka_saver;

import com.example.biedronka_saver.model.entity.Group;
import com.example.biedronka_saver.model.entity.GroupMember;
import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.model.enums.Role;
import com.example.biedronka_saver.repository.ReceiptRepository;
import com.example.biedronka_saver.service.implementation.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {
    @InjectMocks
    private ReceiptService receiptService;

    @Mock
    private ReceiptRepository receiptRepository;

    User userOwner;
    Group group;
    GroupMember groupMember;
    Receipt receipt;

    @BeforeEach
    public void setup() {
        receipt = Receipt.builder()
                .id(1L)
                .payer(groupMember)
                .date(LocalDateTime.now())
                .storeName("test")
                .totalAmount(BigDecimal.ZERO)
                .group(group)
                .build();

        group = Group.builder()
                .id(1L)
                .name("Grupa wakacyjna 2026")
                .joinCode("1234WSAD")
                .build();

        groupMember = GroupMember.builder()
                .id(1L)
                .group(group)
                .displayName("Janusz")
                .user(userOwner)
                .build();

        userOwner = User.builder()
                .id(1L)
                .username("owner")
                .email("owner@example.com")
                .password("owner")
                .role(Role.USER)
                .build();
    }
}
