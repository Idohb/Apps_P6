package com.p6.apps.controller;
import com.p6.apps.controller.dto.user.RegisterRequest;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("logins")
    public ResponseEntity<List<User>> getLogins() {
        try {
            return ResponseEntity.ok(userService.getLogins());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
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

}
