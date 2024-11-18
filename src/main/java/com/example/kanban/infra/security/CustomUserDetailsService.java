package com.example.kanban.infra.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user_E".equals(username)) {
            return new User(
                    "user_E",
                    "$2b$12$NHXm/37Hr8j4KnotybKLROAW7yuaekj9z6.QIBwBYGgU8PZex8Hr2",
                    Collections.emptyList()
            );
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
