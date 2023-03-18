package com.p6.apps.service.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankOperation {
    private Long idBankOperation;
    private LocalDateTime date;
    private double amount;
}
