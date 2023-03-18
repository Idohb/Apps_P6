package com.p6.apps.model.repository;

import com.p6.apps.model.entity.BankOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOperationRepository extends JpaRepository<BankOperationEntity,Long> {
}
