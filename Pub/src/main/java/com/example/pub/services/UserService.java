package com.example.pub.services;

import com.example.pub.models.DTOs.UserDTO;
import com.example.pub.models.User;
import com.example.pub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> users = (List<User>)(userRepository.findAll());
        return convertUsersToDTOs(users);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void payForOrder(User user, Integer price) {
        user.pay(price);
    }

    private List<UserDTO> convertUsersToDTOs(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for(int i = 0; i < users.size();i++) {
            UserDTO userDTO = new UserDTO(users.get(i));
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
