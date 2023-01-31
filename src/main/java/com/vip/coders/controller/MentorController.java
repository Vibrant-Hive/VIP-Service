package com.vip.coders.controller;

import com.vip.coders.model.MentorResponse;
import com.vip.coders.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @PostMapping(path = "/updateProfile")
    @CrossOrigin()
    public boolean updateProfile(@RequestParam String fullName, @RequestParam String skills, @RequestParam String role, @RequestParam boolean active,
                          @RequestParam int experience, @RequestParam long userId, @RequestParam String availability,
                          @RequestParam String designation, @RequestParam String languages, @RequestParam String zoomLink,
                          @RequestParam(value = "resume", required = false) MultipartFile resume, @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {
        return mentorService.updateProfile(userId, fullName, active, skills, role,  experience, designation, languages, zoomLink, availability, resume, photo);
    }

    @GetMapping(path = "/availableMentors")
    public List<MentorResponse> availableMentors() {
        return mentorService.availableMentors();
    }

    @GetMapping(path = "/appliedMentors")
    public List<MentorResponse> appliedMentors() {
        return mentorService.appliedMentors();
    }

    @GetMapping(path = "/approveMentor")
    public boolean approveMentor(@RequestParam long userId, @RequestParam int rate) {
        return mentorService.approveMentor(userId, rate);
    }

}
