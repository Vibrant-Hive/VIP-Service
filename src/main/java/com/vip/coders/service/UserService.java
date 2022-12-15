package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if (getUser(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return user;
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(long userId) {
        return this.userRepository.findById(userId);
    }
}
