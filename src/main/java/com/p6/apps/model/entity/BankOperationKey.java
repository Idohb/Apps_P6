package com.p6.apps.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class BankOperationKey implements Serializable {
    @Column(name = "user_id_user")
    Long userId;

    @Column(name = "bank_id_bank")
    Long bankId;
}
