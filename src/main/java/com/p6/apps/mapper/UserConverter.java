package com.p6.apps.mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.service.data.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    private final ObjectMapper objectMapper;

    public UserConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public User mapperUser(UserEntity userEntity) {
        return objectMapper.convertValue(userEntity, User.class);
    }

    public List<User> mapperUser(List<UserEntity> userEntityList){
        return userEntityList.stream().map(this::mapperUser).collect(Collectors.toList());
    }

}
