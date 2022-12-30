package com.p6.apps.controller;
import com.p6.apps.controller.dto.user.FriendRequest;
import com.p6.apps.service.FriendService;
import com.p6.apps.controller.dto.user.RegisterRequest;
import com.p6.apps.controller.dto.user.UserRequest;
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

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateLogin(@PathVariable("id") final Long id, @RequestBody UserRequest user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") final Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUsers() {
        userService.deleteUsers();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("login/{id}")
    public ResponseEntity<User> getLogin(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(userService.getLogin(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("signon")
    public ResponseEntity<User> register(@RequestBody RegisterRequest user) {
        return ResponseEntity.ok(userService.addUser(user));
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
