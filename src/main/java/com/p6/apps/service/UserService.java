package com.p6.apps.service;

import com.p6.apps.controller.dto.user.RegisterRequest;
import com.p6.apps.controller.dto.user.UserRequest;
import com.p6.apps.mapper.UserConverter;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    public UserService(UserConverter userConverter, UserRepository userRepository, ModelMapper modelMapper) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<User> getLogins() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream()
                .map(entity -> modelMapper.map(entity, User.class))
                .collect(Collectors.toList());
    }

    public User getLogin(final Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return modelMapper.map(userEntity, User.class);
    }

    public User addUser(RegisterRequest userRequest) {
        //create an custom exception
        if(!this.verifyEmailRedundant(userRequest)) { return new User();}
        UserEntity userEntity = new UserEntity(0L,
                userRequest.getFirst_name(),
                userRequest.getLast_name(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                0,
                new ArrayList<>(), // debtor
                new ArrayList<>(), // creditor
                new ArrayList<>(), // friend
                new ArrayList<>(),  // bank
                new ArrayList<>() // bankOperation
        );
        UserEntity entity = userRepository.save(userEntity);
        return modelMapper.map(entity, User.class);
    }

    public User updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(userEntity, userRequest);
        userEntity = userRepository.save(userEntity);
        return modelMapper.map(
                userEntity,
                User.class
        );
//        return userConverter.mapperUser(userEntity);
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

    private boolean verifyEmailRedundant (RegisterRequest userRequest){
            Optional<UserEntity> usrNonRedundant = userRepository.findByEmail(userRequest.getEmail());
            return usrNonRedundant.isEmpty();
    }
}

