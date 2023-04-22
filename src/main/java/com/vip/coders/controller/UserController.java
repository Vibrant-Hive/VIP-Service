package com.vip.coders.controller;


import com.vip.coders.entity.User;
import com.vip.coders.model.LoggedInUserResponse;
import com.vip.coders.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/validateUser")
    public LoggedInUserResponse validateUser(@RequestParam String userName, @RequestParam String password) {
        LoggedInUserResponse loggedInUserResponse = LoggedInUserResponse.builder().build();
        User user = userService.getUser(userName);
        if (user != null && user.getPassword().equals(password)) {
            BeanUtils.copyProperties(user, loggedInUserResponse);
            return loggedInUserResponse;
        }
        return loggedInUserResponse;
    }

    @PostMapping(path = "/createAccount")
    public User createAccount(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/getUser")
    public User getUser(@RequestParam long userId) {return userService.getUserById(userId);}


}
