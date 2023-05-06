package com.vip.coders.controller;

import com.vip.coders.entity.SupportRequest;
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
    public boolean updateProfile(@RequestParam String fullName, @RequestParam Integer skillSetId, @RequestParam String role, @RequestParam boolean active,
                          @RequestParam int experience, @RequestParam long userId, @RequestParam String availability,
                          @RequestParam String designation, @RequestParam String languages, @RequestParam String zoomLink,
                          @RequestParam(value = "resume", required = false) MultipartFile resume, @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {
        return mentorService.updateProfile(userId, fullName, active, skillSetId, role,  experience, designation, languages, zoomLink, availability, resume, photo);
    }

    @GetMapping(path = "/availableMentors")
    public List<MentorResponse> availableMentors() {
        return mentorService.availableMentors();
    }

    @GetMapping(path = "/requestForSupport")
    public SupportRequest requestForSupport(@RequestParam int learnerId, @RequestParam int mentorId){
        return mentorService.requestForSupport(learnerId, mentorId);
    }

    @GetMapping(path = "/getSupportRequest")
    public SupportRequest getSupportRequest(@RequestParam int learnerId, @RequestParam int mentorId){
        return mentorService.getSupportRequest(learnerId, mentorId);
    }

}
