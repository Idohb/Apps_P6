package com.p6.apps.service;
import com.p6.apps.controller.dto.user.FriendRequest;
import com.p6.apps.mapper.UserConverter;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.UserRepository;
import com.p6.apps.service.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    public User addFriend(FriendRequest friendRequest) {
        if(!this.verifyFriendRedundant(friendRequest)) { return new User();}
        List<UserEntity> userEntityList;
        UserEntity userCurrent = userRepository.findById(friendRequest.getUserCurrent()).orElseThrow( () -> new NoSuchElementException("") );
        UserEntity userFriend = userRepository.findByEmail(friendRequest.getEmail()).orElseThrow( () -> new NoSuchElementException("") );
        userEntityList = userCurrent.getFriend();
        userEntityList.add(userFriend);
        userCurrent.setFriend(userEntityList);
        UserEntity userEntity = userRepository.save(userFriend);
        return userConverter.mapperUser(userEntity);
    }

    public List<User> getFriends(Long idUser) {
        UserEntity userEntity = userRepository.findById(idUser).orElseThrow(() -> new NoSuchElementException("Id " + idUser + " not found"));
        return userConverter.mapperUser(userEntity.getFriend());

    }

    private boolean verifyFriendRedundant (FriendRequest friendRequest){
        UserEntity userFriend = userRepository.findByEmail(friendRequest.getEmail()).orElseThrow( () -> new NoSuchElementException("") );
        Optional<UserEntity> friendNonRedundant = userRepository.findByFriend(userFriend);
        return friendNonRedundant.isEmpty();
    }


}