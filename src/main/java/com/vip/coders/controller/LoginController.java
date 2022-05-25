package com.vip.coders.controller;


import com.vip.coders.entity.User;
import com.vip.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/validateUser")
    public User validateUser(@RequestParam String email, @RequestParam String password){
        User user = userService.getUser(email);
        if(user != null && user.getPassword().equals(password)){
            user.setResume(null);
            return user;
        }
        return null;
    }

    @PostMapping(path = "/createAccount")
    @CrossOrigin()
    public User createAccount(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping(path = "/apply")
    @CrossOrigin()
    public boolean apply(@RequestParam String fullName, @RequestParam String skills,
                         @RequestParam int experience, @RequestParam long userId,
                         @RequestBody MultipartFile resume) throws IOException {
        return userService.apply(userId, fullName, skills, experience, resume);
    }
}
