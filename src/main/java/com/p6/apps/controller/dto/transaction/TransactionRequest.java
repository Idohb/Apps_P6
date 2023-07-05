package com.p6.apps.controller.dto.transaction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequest {
    @NotEmpty(message = "Description is required.")
    @Size(min = 3, max = 45, message = "The length must be between 3 to 45 characters.")
    private String description;
    @Min(value = 0)
    private double amountTransaction;
    private LocalDateTime timeTransaction;
    private double commission;
//    private int type;
    @NotNull
    private Long creditor;
    @NotNull
    private Long debtor;
}
