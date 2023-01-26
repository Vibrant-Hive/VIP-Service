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

    public User saveUser(User user) throws Exception {
        if (getUser(user.getMobileNo()) != null) {
            throw new Exception("Mobile No Already Exists");
        }
        if (getUser(user.getEmail()) != null) {
            throw new Exception("Email Already Exists");
        }
        return userRepository.save(user);
    }

    public User getUser(String userName) {
        User user = userRepository.findByEmail(userName);
        if(user == null){
            user = userRepository.findByMobileNo(userName);
        }
        return user;
    }

    public User getUserById(long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

}
