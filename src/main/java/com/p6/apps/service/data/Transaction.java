package com.p6.apps.service.data;

import lombok.Data;
import java.util.Date;

@Data
public class Transaction {
    private String description;
    private double amountTransaction;
    private Date timeTransaction;
    private double commission;
}
