package com.p6.apps.service;

import com.p6.apps.controller.dto.user.RegisterRequest;
import com.p6.apps.mapper.UserConverter;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public UserService(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    public List<User> getLogins() {
        return userConverter.mapperUser(userRepository.findAll());
    }

    public User getLogin(final Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return userConverter.mapperUser(userEntity);
    }

    public User addUser(RegisterRequest userRequest) {
        UserEntity userEntity = new UserEntity(0L,
                userRequest.getFirst_name(),
                userRequest.getLast_name(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                0,
                new ArrayList<>()
        );
        userRepository.save(userEntity);
        return userConverter.mapperUser(userEntity);
    }
}
