package com.example.biedronka_saver;

import com.example.biedronka_saver.model.entity.Receipt;
import com.example.biedronka_saver.model.entity.User;
import com.example.biedronka_saver.model.enums.Role;
import com.example.biedronka_saver.repository.ReceiptRepository;
import com.example.biedronka_saver.service.implementation.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {
    @InjectMocks
    private ReceiptService receiptService;

    @Mock
    private ReceiptRepository receiptRepository;

    User userOwner;
    User userAssigned;
    Receipt receipt;

    @BeforeEach
    public void setup() {
        receipt = Receipt.builder()
                .id(1L)
                .owner(userOwner)
                .date(LocalDateTime.now())
                .receiptItems(List.of())
                .discountAmount(BigDecimal.ZERO)
                .storeName("test")
                .totalAmount(BigDecimal.ZERO)
                .build();

        userOwner = User.builder()
                .id(1L)
                .username("owner")
                .email("owner@example.com")
                .password("owner")
                .role(Role.USER)
                .receipts(List.of(receipt))
                .build();

        userAssigned = User.builder()
                .id(2L)
                .username("assigned")
                .email("assigned@example.com")
                .password("assigned")
                .role(Role.USER)
                .receipts(List.of(receipt))
                .build();

        receipt.setAssignedUsers(List.of(userOwner, userAssigned));
    }

    @Test
    public void testGetAllReceiptsAssignedToUserButNotOwner() {
        // given
        List<Receipt> assignedReceipts = List.of(receipt);
        List<Receipt> ownedReceipts = List.of();

        when(receiptRepository.getReceiptsByAssignedUsersId(userAssigned.getId()))
                .thenReturn(assignedReceipts);
        when(receiptRepository.getReceiptsByOwner_Id(userAssigned.getId()))
                .thenReturn(ownedReceipts);

        // when
        List<Receipt> result = receiptService.getAllReceiptsAssignedToUserButNotOwner(userAssigned.getId());

        // then
        assertEquals(1, result.size());
        assertEquals(receipt, result.getFirst());
        verify(receiptRepository).getReceiptsByAssignedUsersId(userAssigned.getId());
        verify(receiptRepository).getReceiptsByOwner_Id(userAssigned.getId());
    }
}
