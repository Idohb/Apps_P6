package com.p6.apps.model.repository;

import com.p6.apps.model.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOperationRepository extends JpaRepository<BankEntity,Long> {
}
