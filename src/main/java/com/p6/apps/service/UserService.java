package com.p6.apps.service;

import com.p6.apps.controller.dto.user.UserRequest;
import com.p6.apps.mapper.UserConverter;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.User;
import org.springframework.stereotype.Service;

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

    public User updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(userEntity, userRequest);
        userEntity = userRepository.save(userEntity);
        return userConverter.mapperUser(userEntity);
    }

    private void updateEntity(UserEntity userEntity, UserRequest userRequest) {

        if (userRequest.getFirst_name() != null)
            userEntity.setFirst_name(userRequest.getFirst_name());

        if (userRequest.getLast_name() != null)
            userEntity.setLast_name(userRequest.getLast_name());

        if (userRequest.getEmail() != null)
            userEntity.setEmail(userRequest.getEmail());

        if (userRequest.getPassword() != null)
            userEntity.setPassword(userRequest.getPassword());

    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }
    public void deleteUsers() {
        userRepository.deleteAll();
    }
}
