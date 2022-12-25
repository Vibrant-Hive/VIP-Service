package com.vip.coders.controller;

import com.vip.coders.entity.User;
import com.vip.coders.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping(path = "/apply")
    @CrossOrigin()
    public boolean apply(@RequestParam String fullName, @RequestParam String skills,
                         @RequestParam int experience, @RequestParam long userId,
                         @RequestParam String designation, @RequestParam String languages,
                         @RequestParam("document") List<MultipartFile> multipartFiles) throws IOException {
        return dashboardService.apply(userId, fullName, skills, experience, designation, languages, multipartFiles);
    }

    @GetMapping(path = "/availableMentors")
    public List<User> availableMentors() {
        return dashboardService.availableMentors();
    }

}
