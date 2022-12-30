package com.p6.apps.controller.dto.transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequest {
    private String description;
    private double amountTransaction;
    private Date timeTransaction;
    private double commission;
    private Long creditor;
    private Long debtor;
}
