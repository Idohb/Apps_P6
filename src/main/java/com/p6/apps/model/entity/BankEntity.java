package com.p6.apps.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="bank")
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBank;

    private String iban;
    private double amountBank;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "idBankOperation")
    private List<BankOperationEntity> bankOperation;
}
