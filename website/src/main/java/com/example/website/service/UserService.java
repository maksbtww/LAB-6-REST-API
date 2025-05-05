package com.example.website.service;

import com.example.website.entity.User;
import com.example.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String username, String password) {
        return userRepository.findOneByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    public boolean registerUser(String username, String password) {
        if (userRepository.findOneByUsername(username).isPresent()) {
            return false; // Пользователь уже существует
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // В реальном проекте используйте хэширование!
        newUser.setRole("user");

        userRepository.save(newUser);
        return true;
    }
}