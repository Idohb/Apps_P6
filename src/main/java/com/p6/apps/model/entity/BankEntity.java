package com.p6.apps.model.entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


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
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "bankEntity")
    private List<BankOperationEntity> bankOperation;

    @ManyToMany
    private Set<UserEntity> operationUser;

}
