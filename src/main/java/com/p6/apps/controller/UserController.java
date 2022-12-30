package com.p6.apps.controller;
import com.p6.apps.controller.dto.user.FriendRequest;
import com.p6.apps.service.FriendService;
import com.p6.apps.service.UserService;
import com.p6.apps.service.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {

    private final UserService userService;
    private final FriendService friendService;


    @Autowired
    public UserController(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    @GetMapping("logins")
    public ResponseEntity<List<User>> getLogins() {
        try {
            return ResponseEntity.ok(userService.getLogins());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("friend")
    public ResponseEntity<Long> addFriend(@RequestBody FriendRequest friendRequest) {
        try {
            return ResponseEntity.ok(friendService.addFriend(friendRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
