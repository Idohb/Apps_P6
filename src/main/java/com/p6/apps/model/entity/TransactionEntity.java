package com.p6.apps.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;

    private String description;
    private double amountTransaction;
    private LocalDateTime timeTransaction;
    private double commission;

    @ManyToOne
    @JoinColumn(name="creditor")
    private UserEntity creditor;

    @ManyToOne
    @JoinColumn(name="debtor")
    private UserEntity debtor;
}
