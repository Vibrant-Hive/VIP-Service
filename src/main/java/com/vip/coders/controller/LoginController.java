package com.vip.coders.controller;


import com.vip.coders.entity.User;
import com.vip.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/validateUser")
    public Long validateUser(@RequestParam String email, @RequestParam String password){
        User user = userService.getUser(email);
        if(user != null && user.getPassword().equals(password)){
            return user.getId();
        }
        return null;
    }

    @PostMapping(path = "/createAccount")
    @CrossOrigin()
    public User createAccount(@RequestBody User user){
        return userService.saveUser(user);
    }
}
