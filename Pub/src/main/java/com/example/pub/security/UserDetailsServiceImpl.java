package com.example.pub.security;

import com.example.pub.models.User;
import com.example.pub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String[] roles = {"ROLE_" + user.getRole().name()};
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())
                    .roles(roles)
                    .accountLocked(!user.isActive())
                    .build();

            return userDetails;
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
