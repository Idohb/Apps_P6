package com.p6.apps.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="bankoperation")
public class BankOperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBankOperation;
    private LocalDateTime date;
    private double amount;
    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "user_id_user")
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("id_bank")
    @JoinColumn(name = "bank_id_bank")
    private BankEntity bankEntity;



}
