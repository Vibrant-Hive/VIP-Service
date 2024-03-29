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
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    public User getUser(@RequestParam long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(path = "/getAllUsers")
    public String getAllUsers() throws NoSuchFieldException, IllegalAccessException {
        return HTMLTableConverter.convertListToHTMLTable(userService.getAllUsers());
    }

    @PostMapping(path = "/registerUserEvent")
    public void registerUserEvent(@RequestBody UserEvent userEvent) {
        userEvent.setEventDate(new Date(System.currentTimeMillis() + 19800000));
        userEventRepository.save(userEvent);
    }

    @GetMapping(path = "/getAllUserEvents")
    public String getAllUserEvents() throws NoSuchFieldException, IllegalAccessException {
        List<UserEvent> userEvents = (List<UserEvent>) userEventRepository.findAll();
        AtomicLong count = new AtomicLong(1L);
        userEvents = userEvents.stream().filter(UserController::isRecent).
                peek(userEvent -> userEvent.setId(count.getAndIncrement())).
                collect(Collectors.toList());
        Collections.reverse(userEvents);
        return HTMLTableConverter.convertListToHTMLTable(userEvents);
    }

    private static boolean isRecent(UserEvent userEvent) {
        return userEvent.getEventDate().after(new Date(System.currentTimeMillis() - 86400000));
    }

}
