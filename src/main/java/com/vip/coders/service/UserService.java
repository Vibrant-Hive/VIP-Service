package com.vip.coders.service;

import com.vip.coders.entity.User;
import com.vip.coders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        if (getUser(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return user;
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean apply(long userId, String fullName, String skills, int experience, String designation, String languages, MultipartFile resume) throws IOException {
        User user = this.userRepository.findById(userId).orElse(User.builder().build());
        user.setExperience(experience);
        user.setFullName(fullName);
        user.setResume(resume.getBytes());
        user.setSkills(skills);
        user.setRole("MENTOR");
        user.setActive(false);
        user.setDesignation(designation);
        user.setLanguages(languages);
        this.userRepository.save(user);
        return true;
    }

    public List<User> availableMentors() {
        return this.userRepository.findByRoleAndActive("MENTOR", true);
    }

    public List<User> appliedMentors() {
        return this.userRepository.findByRoleAndActive("MENTOR", false);
    }

    public byte[] downloadResume(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return user.map(User::getResume).orElse(null);
    }

    public boolean approveMentor(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        user.ifPresent(value -> {
            user.get().setActive(true);
            this.userRepository.save(value);
        });
        return true;
    }


    public Optional<User> getUserById(long userId) {
        return this.userRepository.findById(userId);
    }
}
