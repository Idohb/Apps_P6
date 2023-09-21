package com.p6.apps.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
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
    private String roles;
//    private int enable;
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

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")}
//    )
//    private List<RoleEntity> roles = new ArrayList<>();

}
