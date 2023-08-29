package com.p6.apps.service;

import com.p6.apps.model.entity.UserEntity;
import com.p6.apps.model.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(usernameOrEmail).orElseThrow(() -> new NoSuchElementException(""));
        if (userEntity != null) {
            return new org.springframework.security.core.userdetails.User(userEntity.getEmail()
                    , userEntity.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(userEntity.getRoles()))
/*                    userEntity.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())*/);
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
