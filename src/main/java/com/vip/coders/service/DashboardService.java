package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    public boolean apply(long userId, String fullName, String skills, int experience, String designation, String languages, String zoomLink, String availability, List<MultipartFile> files) throws IOException {
        User user = this.userRepository.findById(userId).orElse(User.builder().build());
        user.setExperience(experience);
        user.setFullName(fullName);
        user.setSkills(skills);
        user.setRole("MENTOR");
        user.setActive(false);
        user.setDesignation(designation);
        user.setLanguages(languages);
        user.setZoomLink(zoomLink);
        user.setAvailability(availability);
        if (!files.isEmpty()) {
            user.setResume(files.get(0).getBytes());
            user.setPhoto(files.get(1).getBytes());
        }
        this.userRepository.save(user);
        return true;
    }

    public List<User> availableMentors() {
        List<User> mentors = this.userRepository.findByRoleAndActive("MENTOR", true);
        List<User> master = this.userRepository.findByRoleAndActive("MASTER", true);
        mentors.addAll(master);
        return mentors;
    }
}
