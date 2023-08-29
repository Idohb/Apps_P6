package com.p6.apps.controller.dto.bank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankRequest {
    @NotEmpty(message = "iban is required.")
    private String iban;
    private double amountBank;
    @NotNull
    private Long user;
}
