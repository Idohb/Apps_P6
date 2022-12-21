package com.p6.apps.service;
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

    public Long addFriend(Long lCreditor, Long lDebtor) {
        List<UserEntity> userEntityList;
        UserEntity usrCreditor = userRepository.findById(lCreditor).orElseThrow( () -> new NoSuchElementException("") );
        UserEntity usrDebtor = userRepository.findById(lDebtor).orElseThrow( () -> new NoSuchElementException("") );
        userEntityList = usrCreditor.getFriend();
        userEntityList.add(usrDebtor);
        usrCreditor.setFriend(userEntityList);
        userRepository.save(usrCreditor);
        return 0L;
    }
}
