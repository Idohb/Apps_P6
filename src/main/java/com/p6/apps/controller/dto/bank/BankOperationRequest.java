package com.p6.apps.controller.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankOperationRequest {
    private LocalDateTime date;
    private double amount;
    private Long idUser;
    private Long idBank;
}
