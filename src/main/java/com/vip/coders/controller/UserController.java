package com.vip.coders.controller;


import com.vip.coders.entity.User;
import com.vip.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/validateUser")
    public User validateUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            user.setResume(null);
            return user;
        }
        return null;
    }

    @PostMapping(path = "/createAccount")
    @CrossOrigin()
    public User createAccount(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/getUser")
    public Optional<User> getUser(@RequestParam long userId) {return userService.getUserById(userId);}

}
