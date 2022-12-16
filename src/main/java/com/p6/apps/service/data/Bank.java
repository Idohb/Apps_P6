package com.p6.apps.service.data;
import lombok.Data;

@Data
public class Bank {
    private Long idBank;
    private String iban;
    private double amountBank;
}
