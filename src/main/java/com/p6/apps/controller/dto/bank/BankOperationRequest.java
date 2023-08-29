package com.p6.apps.controller.dto.bank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankOperationRequest {
    private LocalDateTime date;
    @NotNull
    @Min(value = 0)
    private double amount;
    @NotNull
    private Long idUser;
    @NotNull
    private Long idBank;
}
