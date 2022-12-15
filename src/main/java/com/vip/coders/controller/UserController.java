package com.vip.coders.controller;


import com.vip.coders.entity.User;
import com.vip.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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

    @PostMapping(path = "/apply")
    @CrossOrigin()
    public boolean apply(@RequestParam String fullName, @RequestParam String skills,
                         @RequestParam int experience, @RequestParam long userId,
                         @RequestParam String designation, @RequestParam String languages,
                         @RequestBody MultipartFile resume) throws IOException {
        return userService.apply(userId, fullName, skills, experience, designation, languages, resume);
    }

    @GetMapping(path = "/availableMentors")
    public List<User> availableMentors() {
        return userService.availableMentors();
    }

    @GetMapping(path = "/appliedMentors")
    public List<User> appliedMentorsUrl() {
        return userService.appliedMentors();
    }

    @GetMapping(path = "/downloadResume")
    public ResponseEntity<byte[]> downloadResume(@RequestParam long userId) {

        byte[] resume = userService.downloadResume(userId);
        String fileName = "resume.docx";

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(resume.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return new ResponseEntity<>(resume, respHeaders, HttpStatus.OK);
    }


    @GetMapping(path = "/approveMentor")
    public boolean approveMentor(@RequestParam long userId, @RequestParam int rate) {
        return userService.approveMentor(userId, rate);
    }

    @GetMapping(path = "/getUser")
    public Optional<User> getUser(@RequestParam long userId) {return userService.getUserById(userId);}

}
