package com.p6.apps.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
}
