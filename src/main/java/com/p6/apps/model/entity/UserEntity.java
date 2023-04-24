package com.p6.apps.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    @Min(value = 0)
    private double balance;

    @OneToMany(mappedBy = "debtor")
    private List<TransactionEntity> debtor;

    @OneToMany(mappedBy = "creditor")
    private List<TransactionEntity> creditor;

    @ManyToMany
    @JoinTable(
            name="friend",
            joinColumns = @JoinColumn(name="user_iduser"),
            inverseJoinColumns = @JoinColumn(name="user_iduser1")
    )
    private List<UserEntity> friend;

    @OneToMany(mappedBy = "idBank")
    private List<BankEntity> ownBank;

    @OneToMany(mappedBy = "userEntity")
    private List<BankOperationEntity> userOperation;

    @ManyToMany
    private Set<BankEntity> operationBank;
}
