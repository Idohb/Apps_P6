package com.p6.apps.model.repository;

import com.p6.apps.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByFriend(UserEntity userEntity);
}
