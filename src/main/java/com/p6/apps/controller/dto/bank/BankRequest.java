package com.p6.apps.controller.dto.bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankRequest {
    private String iban;
    private double amountBank;
    private Long user;
}
