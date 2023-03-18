package com.p6.apps.model.repository;
import com.p6.apps.model.entity.BankEntity;
import com.p6.apps.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity,Long>{
    Optional<BankEntity> findByUserAndIdBank(UserEntity userEntity, Long idBank);
}
