package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.exception.AlreadyFoundException;
import com.vip.coders.model.UserResponse;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if (getUser(user.getMobileNo()) != null) {
            throw new AlreadyFoundException("Mobile No Already Exists");
        }
        return userRepository.save(user);
    }

    public User getUser(String userName) {
        return userRepository.findByMobileNo(userName);
    }

    public User getUserById(long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = (List<User>) this.userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> {
            UserResponse userResponse = UserResponse.builder().build();
            BeanUtils.copyProperties(user, userResponse);
            userResponses.add(userResponse);
        });
        Collections.reverse(userResponses);
        return userResponses;
    }
}
