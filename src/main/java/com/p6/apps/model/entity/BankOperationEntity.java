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
    private Long idBankOperation;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id_user")
    private UserEntity userEntity;
    @ManyToOne
    @MapsId("bankId")
    @JoinColumn(name = "bank_id_bank")
    private BankEntity bankEntity;

    private LocalDateTime date;
    private double amount;

}
