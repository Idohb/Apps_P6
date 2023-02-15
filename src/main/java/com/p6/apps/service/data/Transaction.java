package com.p6.apps.service.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long idTransaction;
    private String description;
    private double amountTransaction;
    private LocalDateTime timeTransaction;
    private double commission;
}
