package com.p6.apps.service;

import com.p6.apps.controller.dto.user.RegisterRequest;
import com.p6.apps.controller.dto.user.UserRequest;
import com.p6.apps.model.entity.RoleEntity;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.RoleRepository;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.User;
import com.p6.apps.util.TbConstants;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
//        List<RoleEntity> roleEntities = new ArrayList<>();
//        RoleEntity role = roleRepository.findByName(TbConstants.Roles.USER);
//        roleEntities.add(role);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());
        final int DEFAULT_AUTH = 1;

        //create an custom exception
        if(!this.verifyEmailRedundant(userRequest)) { return new User();}
        UserEntity userEntity = new UserEntity(0L,
                userRequest.getFirst_name(),
                userRequest.getLast_name(),
                userRequest.getEmail(),
                encodedPassword,
                0,
                "ROLE_USER",
                new ArrayList<>(),  // debtor
                new ArrayList<>(),  // creditor
                new ArrayList<>(),  // friend
                new ArrayList<>(),  // bank
                new ArrayList<>(),  // bankOperation
                new HashSet<>()    // operationBank
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
    }

    public User searchEmailAndPassword(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException(""));
        return modelMapper.map(
                userEntity,
                User.class
        );
    }
    private void updateEntity(UserEntity userEntity, UserRequest userRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());

        if (userRequest.getFirst_name() != null)
            userEntity.setFirst_name(userRequest.getFirst_name());

        if (userRequest.getLast_name() != null)
            userEntity.setLast_name(userRequest.getLast_name());

        if (userRequest.getEmail() != null)
            userEntity.setEmail(userRequest.getEmail());

        if (userRequest.getPassword() != null)
            userEntity.setPassword(encodedPassword);
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

    public User findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException(""));
        return modelMapper.map(
                userEntity,
                User.class
        );
    }

}

