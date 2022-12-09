package com.p6.apps.service;

import com.p6.apps.mapper.UserConverter;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
