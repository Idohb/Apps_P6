package com.p6.apps.mapper;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.service.data.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    public User mapperUser(UserEntity userEntity) {
                User user = new User();
                user.setIdUser(userEntity.getIdUser());
                user.setFirst_name(userEntity.getFirst_name());
                user.setLast_name(userEntity.getLast_name());
                user.setEmail(userEntity.getEmail());
                user.setBalance(userEntity.getBalance());
                user.setPassword(userEntity.getPassword());
                return user;
    }

    public List<User> mapperUser(List<UserEntity> userEntityList){
        return userEntityList.stream().map(this::mapperUser).collect(Collectors.toList());
    }

}
