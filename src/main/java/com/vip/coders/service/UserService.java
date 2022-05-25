package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if(getUser(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return user;
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean apply(long userId, String fullName, String skills, int experience, MultipartFile resume) throws IOException {
        User user = this.userRepository.findById(userId).orElse(User.builder().build());
        user.setExperience(experience);
        user.setFullName(fullName);
        user.setResume(resume.getBytes());
        user.setSkills(skills);
        user.setRole("MENTOR");
        user.setActive(false);
        this.userRepository.save(user);
        return true;
    }
}
