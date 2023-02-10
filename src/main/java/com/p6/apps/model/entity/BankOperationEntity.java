package com.p6.apps.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="bank_operation")
public class BankOperationEntity {
    @Id
    private Long idBankOperation;
    private LocalDateTime date;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private UserEntity userEntityList;

    @ManyToOne
    @JoinColumn(name = "bank_id_bank")
    private BankEntity bankEntity;



}
