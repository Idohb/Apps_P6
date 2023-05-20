package com.p6.apps.model.repository;
import com.p6.apps.model.entity.TransactionEntity;
import com.p6.apps.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long>{
    Optional<List<TransactionEntity>> findByCreditor_IdUser(Long creditor);
}
