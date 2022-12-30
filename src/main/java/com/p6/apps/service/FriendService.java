package com.p6.apps.service;
import com.p6.apps.controller.dto.user.FriendRequest;
import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FriendService {
    @Autowired
    UserRepository userRepository;

    public Long addFriend(FriendRequest friendRequest) {
        List<UserEntity> userEntityList;
        UserEntity userCurrent = userRepository.findById(friendRequest.getUserCurrent()).orElseThrow( () -> new NoSuchElementException("") );
        UserEntity userFriend = userRepository.findByEmail(friendRequest.getEmail()).orElseThrow( () -> new NoSuchElementException("") );
        userEntityList = userCurrent.getFriend();
        userEntityList.add(userFriend);
        userCurrent.setFriend(userEntityList);
        userRepository.save(userFriend);
        return 0L;
    }
}