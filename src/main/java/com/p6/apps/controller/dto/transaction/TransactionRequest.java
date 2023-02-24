package com.p6.apps.controller.dto.transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequest {
    private String description;
    private double amountTransaction;
    private LocalDateTime timeTransaction;
    private double commission;
    private int type;
    private Long creditor;
    private Long debtor;
}
