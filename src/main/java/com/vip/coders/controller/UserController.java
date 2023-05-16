package com.vip.coders.controller;


import com.vip.coders.entity.User;
import com.vip.coders.entity.UserEvent;
import com.vip.coders.model.UserResponse;
import com.vip.coders.repository.UserEventRepository;
import com.vip.coders.service.UserService;
import com.vip.coders.util.HTMLTableConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserEventRepository userEventRepository;

    @GetMapping(path = "/validateUser")
    public UserResponse validateUser(@RequestParam String userName, @RequestParam String password) {
        UserResponse userResponse = UserResponse.builder().build();
        User user = userService.getUser(userName);
        if (user != null && user.getPassword().equals(password)) {
            BeanUtils.copyProperties(user, userResponse);
            return userResponse;
        }
        return userResponse;
    }

    @PostMapping(path = "/createAccount")
    public User createAccount(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "/getUser")
    public User getUser(@RequestParam long userId) {return userService.getUserById(userId);}

    @GetMapping(path = "/getAllUsers")
    public List<UserResponse> getAllUsers() {return userService.getAllUsers();}

    @PostMapping(path="/registerUserEvent")
    public void registerUserEvent(@RequestBody UserEvent userEvent){
        userEvent.setEventDate(new Date(System.currentTimeMillis() + 19800000));
        userEventRepository.save(userEvent);
    }

    @GetMapping(path="/getAllUserEvents")
    public String getAllUserEvents() throws NoSuchFieldException, IllegalAccessException {
        List<UserEvent> userEvents = (List<UserEvent>) userEventRepository.findAll();
        Collections.reverse(userEvents);
        return HTMLTableConverter.convertListToHTMLTable(userEvents);
    }

}
